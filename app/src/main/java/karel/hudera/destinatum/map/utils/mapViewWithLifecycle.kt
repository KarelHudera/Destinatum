package karel.hudera.destinatum.map.utils

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun mapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dpToPx(context)
    val screenHeight = configuration.screenHeightDp.dpToPx(context)
    val zoomLevel = calculateMinZoomLevel(screenWidth, screenHeight)

    val mapView = remember {
        MapView(context).apply {
            id = View.generateViewId()
            setTileSource(TileSourceFactory.MAPNIK)
            controller.setZoom(9.5)
            controller.setCenter(GeoPoint(39.5696, 2.6502)) // Center on Palma de Mallorca

            setMultiTouchControls(true)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            setHorizontalMapRepetitionEnabled(true)
            setVerticalMapRepetitionEnabled(false)
            setScrollableAreaLimitLatitude(
                MapView.getTileSystem().maxLatitude,
                MapView.getTileSystem().minLatitude,
                0
            )
            minZoomLevel = zoomLevel

            // Add a marker
            val marker = Marker(this)
            marker.position = GeoPoint(39.5696, 2.6502)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = "Palma de Mallorca"

            overlays.add(marker)
        }
    }

    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}