package karel.hudera.destinatum.map

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import karel.hudera.destinatum.map.utils.mapViewWithLifecycle
import org.osmdroid.views.MapView

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onLoad: ((map: MapView) -> Unit)? = null
) {
    val mapViewState = mapViewWithLifecycle()

    AndroidView(
        factory = { mapViewState },
        modifier = modifier
    ) { mapView ->
        onLoad?.invoke(mapView)
    }
}