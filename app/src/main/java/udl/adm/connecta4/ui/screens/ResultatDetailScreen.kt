package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udl.adm.connecta4.ui.NavyDeep

@Composable
fun ResultatDetailScreen(
    logText: String,
    date: String,
    modifier: Modifier = Modifier,
    onBack: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(NavyDeep)
            .padding(16.dp)
    ) {
        if (onBack != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector        = Icons.Filled.ArrowBack,
                        contentDescription = "Enrere",
                        tint               = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.width(4.dp))
                Text(
                    "DETALL DE LA PARTIDA",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight    = FontWeight.Bold,
                        letterSpacing = 2.sp,
                        color         = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        } else {
            Text(
                "DETALL DE LA PARTIDA",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color         = MaterialTheme.colorScheme.onBackground
                )
            )
        }

        Spacer(Modifier.height(4.dp))
        Text(
            date,
            style = MaterialTheme.typography.bodySmall.copy(
                color         = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        )

        Spacer(Modifier.height(12.dp))

        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .shadow(4.dp, RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorScheme.surface
        ) {
            Text(
                text     = logText,
                modifier = Modifier
                    .padding(14.dp)
                    .verticalScroll(scrollState),
                style    = MaterialTheme.typography.bodySmall.copy(
                    color      = MaterialTheme.colorScheme.onSurface,
                    fontFamily = FontFamily.Monospace,
                    lineHeight = 20.sp
                )
            )
        }
    }
}
