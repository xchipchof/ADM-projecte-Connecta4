package udl.adm.connecta4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import udl.adm.connecta4.model.CellState
import udl.adm.connecta4.ui.DiscRed
import udl.adm.connecta4.ui.DiscYellow
import udl.adm.connecta4.ui.HoleColor

@Composable
fun BoardCell(state: CellState, size: Dp, onClick: () -> Unit) {
    val discColor: Color? = when (state) {
        CellState.EMPTY  -> null
        CellState.PLAYER -> DiscRed
        CellState.SYSTEM -> DiscYellow
    }

    Box(
        modifier = Modifier
            .size(size)
            .padding(3.dp)
            .clip(CircleShape)
            .background(
                if (discColor != null)
                    Brush.radialGradient(
                        0.0f to discColor.copy(alpha = 0.9f),
                        0.6f to discColor,
                        1.0f to Color(
                            red   = discColor.red   * 0.55f,
                            green = discColor.green * 0.55f,
                            blue  = discColor.blue  * 0.55f
                        )
                    )
                else
                    Brush.radialGradient(
                        0.0f to Color(0xFF1A1A38),
                        1.0f to HoleColor
                    )
            )
            .clickable { onClick() }
    )
}
