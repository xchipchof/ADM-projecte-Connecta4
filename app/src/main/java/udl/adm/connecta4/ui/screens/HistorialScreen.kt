package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udl.adm.connecta4.model.GameRecord
import udl.adm.connecta4.ui.NavyDeep
import udl.adm.connecta4.viewmodel.HistorialViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HistorialScreen(
    viewModel: HistorialViewModel,
    windowSizeClass: WindowSizeClass,
    onRecordClick: (GameRecord) -> Unit,
    onBack: () -> Unit
) {
    val games by viewModel.games.collectAsState(initial = emptyList())
    val isTablet = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDeep)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp, end = 20.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector        = Icons.Filled.ArrowBack,
                    contentDescription = "Enrere",
                    tint               = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(Modifier.width(4.dp))
            Column {
                Text(
                    "HISTORIAL",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight    = FontWeight.Black,
                        letterSpacing = 4.sp,
                        color         = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    "Partides anteriors",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color         = MaterialTheme.colorScheme.onSurfaceVariant,
                        letterSpacing = 1.sp
                    )
                )
            }
        }

        if (isTablet) {
            Row(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    if (games.isEmpty()) {
                        item { EmptyHistorialMessage() }
                    }
                    items(games) { record ->
                        GameRecordListItem(
                            record     = record,
                            isSelected = record == viewModel.selectedRecord,
                            onClick    = { viewModel.selectRecord(record) }
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
                )

                Box(modifier = Modifier.weight(1f)) {
                    val selected = viewModel.selectedRecord
                    if (selected != null) {
                        ResultatDetailScreen(
                            logText = selected.logText,
                            date    = selected.date,
                            onBack  = null
                        )
                    } else {
                        Box(
                            modifier         = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Selecciona una partida",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            )
                        }
                    }
                }
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                if (games.isEmpty()) {
                    item { EmptyHistorialMessage() }
                }
                items(games) { record ->
                    GameRecordListItem(
                        record     = record,
                        isSelected = false,
                        onClick    = { onRecordClick(record) }
                    )
                }
            }
        }
    }
}

@Composable
private fun GameRecordListItem(
    record: GameRecord,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val bgColor = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else Color.Transparent

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .shadow(if (isSelected) 4.dp else 0.dp, RoundedCornerShape(10.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier          = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    record.date,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color      = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    record.result,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color         = resultColor(record.result),
                        letterSpacing = 1.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun resultColor(result: String) = when (result) {
    "Guanyada"      -> MaterialTheme.colorScheme.secondary
    "Perduda"       -> MaterialTheme.colorScheme.error
    "Empat"         -> MaterialTheme.colorScheme.onSurfaceVariant
    "Temps esgotat" -> MaterialTheme.colorScheme.error.copy(alpha = 0.7f)
    else            -> MaterialTheme.colorScheme.onSurfaceVariant
}

@Composable
private fun EmptyHistorialMessage() {
    Box(
        modifier         = Modifier
            .fillMaxWidth()
            .padding(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "Encara no hi ha partides guardades",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}
