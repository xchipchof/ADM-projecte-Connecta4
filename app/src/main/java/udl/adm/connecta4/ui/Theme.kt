package udl.adm.connecta4.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF2805B4),
    onPrimary = Color.White,
    background = Color(0xFFA39F9F)
)

@Composable
fun Connecta4Theme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, content = content)
}