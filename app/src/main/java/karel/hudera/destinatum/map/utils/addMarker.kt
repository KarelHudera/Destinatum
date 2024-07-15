package karel.hudera.destinatum.map.utils

import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

fun addMarker(mapView: MapView) {
    val marker = Marker(mapView)
    marker.position = GeoPoint(39.5696, 2.6502)
    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
    marker.title = "Palma de Mallorca"

    mapView.overlays.add(marker)
}