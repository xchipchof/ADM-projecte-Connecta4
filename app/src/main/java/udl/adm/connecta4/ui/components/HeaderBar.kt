package udl.adm.connecta4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udl.adm.connecta4.ui.DiscRed
import udl.adm.connecta4.ui.DiscYellow
import udl.adm.connecta4.ui.NavyCard

@Composable
fun HeaderBar(isPlayerTurn: Boolean, timeElapsed: Int, hasTime: Boolean, maxTime: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .background(NavyCard)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TurnIndicator(isPlayerTurn)
        TimerDisplay(timeElapsed, hasTime, maxTime)
    }
}

@Composable
private fun TurnIndicator(isPlayerTurn: Boolean) {
    val discColor = if (isPlayerTurn) DiscRed else DiscYellow
    val label = if (isPlayerTurn) "El teu torn" else "Màquina"

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .border(2.dp, discColor.copy(alpha = 0.5f), CircleShape)
                .padding(3.dp)
                .clip(CircleShape)
                .background(discColor)
        )
        Spacer(Modifier.width(10.dp))
        Column {
            Text(
                text = "TORN",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 2.sp
                )
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = discColor
                )
            )
        }
    }
}

@Composable
private fun TimerDisplay(timeElapsed: Int, hasTime: Boolean, maxTime: Int) {
    val timeLeft = if (hasTime) maxTime - timeElapsed else timeElapsed
    val isUrgent = hasTime && timeLeft <= 10
    val timerColor = when {
        isUrgent -> MaterialTheme.colorScheme.error
        hasTime -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = if (hasTime) "TEMPS RESTANT" else "TEMPS",
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 2.sp
            )
        )
        Text(
            text = "${timeLeft}s",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = timerColor
            )
        )
    }
}
