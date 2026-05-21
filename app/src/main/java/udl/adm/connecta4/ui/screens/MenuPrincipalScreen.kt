package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udl.adm.connecta4.ui.BoardBlue
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.DiscRed
import udl.adm.connecta4.ui.DiscYellow
import udl.adm.connecta4.ui.NavyDeep

@Composable
fun MenuPrincipalScreen(
    onAjudaClick: () -> Unit,
    onComencarClick: () -> Unit,
    onExitClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(NavyDeep, Color(0xFF0E1040), NavyDeep)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TokenRow()

            Spacer(Modifier.height(24.dp))

            Text(
                text = "CONNECT",
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Black,
                    letterSpacing = 6.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Text(
                text = "4",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Black,
                    fontSize = 96.sp,
                    color = DiscYellow,
                    letterSpacing = 0.sp
                ),
                lineHeight = 80.sp
            )

            Spacer(Modifier.height(8.dp))
            Text(
                text = "Quatre en ratlla",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    letterSpacing = 3.sp
                )
            )

            Spacer(Modifier.height(48.dp))

            Button(
                onClick = onComencarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BoardBlue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "COMENÇAR PARTIDA",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
            }

            Spacer(Modifier.height(12.dp))

            OutlinedButton(
                onClick = onAjudaClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    "AJUDA",
                    style = MaterialTheme.typography.labelLarge.copy(letterSpacing = 2.sp)
                )
            }

            Spacer(Modifier.height(4.dp))

            TextButton(
                onClick = onExitClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "SORTIR",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun TokenRow() {
    val colors = listOf(DiscRed, DiscYellow, DiscRed, DiscYellow, DiscRed, DiscYellow)
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            Spacer(Modifier.width(6.dp))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF08081E)
@Composable
fun MenuPrincipalScreenPreview() {
    Connecta4Theme {
        MenuPrincipalScreen({}, {}, {})
    }
}
