package karel.hudera.destinatum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import karel.hudera.destinatum.map.MapView
import karel.hudera.destinatum.ui.theme.DestinatumTheme
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    companion object {
        private const val USER_AGENT = "karel.hudera.destinatum"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()

        setupStatusBarAndWindow()

        configureOsmdroid()

        setContent {
            DestinatumTheme {
                MapView()
            }
        }
    }

    private fun setupStatusBarAndWindow() {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun configureOsmdroid() {
        val ctx = applicationContext
        Configuration.getInstance().load(ctx, ctx.getSharedPreferences("osmdroid", MODE_PRIVATE))
        // Configuration.getInstance().isDebugMapTileDownloader = true
        // Configuration.getInstance().isDebugMode = true
        // Configuration.getInstance().isDebugTileProviders = true
        Configuration.getInstance().userAgentValue = USER_AGENT
    }
}