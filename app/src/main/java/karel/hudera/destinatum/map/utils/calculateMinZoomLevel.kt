package karel.hudera.destinatum.map.utils

import android.content.Context
import kotlin.math.ln



fun calculateMinZoomLevel(screenWidth: Int, screenHeight: Int): Double {
    val zoomFactor = 100.0

    val widthZoomLevel = ln(screenWidth / zoomFactor) / ln(2.0)
    val heightZoomLevel = ln(screenHeight / zoomFactor) / ln(2.0)

    return widthZoomLevel.coerceAtMost(heightZoomLevel)
}

fun Int.dpToPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}