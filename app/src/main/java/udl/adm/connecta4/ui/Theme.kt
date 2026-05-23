package udl.adm.connecta4.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NavyDeep = Color(0xFF08081E)
val NavySurface = Color(0xFF12123A)
val NavyCard = Color(0xFF1A1A50)
val BoardBlue = Color(0xFF1565C0)
val BoardBlueDark = Color(0xFF0D47A1)
val DiscRed = Color(0xFFFF3232)
val DiscYellow = Color(0xFFFFD600)
val HoleColor = Color(0xFF060616)
val PrimaryBlue = Color(0xFF82B1FF)
val OnPrimary = Color(0xFF08081E)

private val DarkColors = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = OnPrimary,
    secondary = DiscYellow,
    onSecondary = Color(0xFF1A1200),
    background = NavyDeep,
    onBackground = Color(0xFFF0F0FF),
    surface = NavySurface,
    onSurface = Color(0xFFE0E0FF),
    surfaceVariant = NavyCard,
    onSurfaceVariant = Color(0xFFBBBBDD),
    error = DiscRed,
    onError = Color.White,
)

@Composable
fun Connecta4Theme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = DarkColors, content = content)
}
