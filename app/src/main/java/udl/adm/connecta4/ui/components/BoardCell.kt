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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import udl.adm.connecta4.model.CellState

@Composable
fun BoardCell(state: CellState, size: Dp, onClick: () -> Unit) {
    val color = when (state) {
        CellState.EMPTY -> Color.LightGray
        CellState.PLAYER -> Color.Red
        CellState.SYSTEM -> Color.Yellow
    }

    Box(
        modifier = Modifier
            .size(size)
            .padding(2.dp)
            .clip(CircleShape)
            .background(color)
            .clickable { onClick() }
    )
}