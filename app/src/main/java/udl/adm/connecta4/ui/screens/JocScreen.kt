package udl.adm.connecta4.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import udl.adm.connecta4.model.GameState
import udl.adm.connecta4.ui.components.BoardCell
import udl.adm.connecta4.ui.components.HeaderBar
import udl.adm.connecta4.viewmodel.GameViewModel


@Composable
fun JocScreen(
    alias: String, size: Int, hasTime: Boolean, maxTime: Int,
    onGameEnd: (String) -> Unit
) {
    val context = LocalContext.current
    val factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GameViewModel(alias, size, hasTime, maxTime) as T
        }
    }
    val viewModel: GameViewModel = viewModel(factory = factory)

    val board by viewModel.board.collectAsState()
    val isPlayerTurn by viewModel.isPlayerTurn.collectAsState()
    val timeElapsed by viewModel.timeElapsed.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(gameState) {
        when (gameState) {
            is GameState.PlayerWins -> Toast.makeText(context, "GAME OVER.... BEN FET, HAS GUANYAT !!", Toast.LENGTH_LONG).show()
            is GameState.SystemWins -> Toast.makeText(context, "GAME OVER.... MALA SORT, HAS PERDUT !!", Toast.LENGTH_LONG).show()
            is GameState.Draw -> Toast.makeText(context, "VAJA.... HEU EMPATAT !!", Toast.LENGTH_LONG).show()
            is GameState.TimeOut -> Toast.makeText(context, "Temps esgotat !. Repeteix sort...", Toast.LENGTH_LONG).show()
            else -> {}
        }
        if (gameState != GameState.Ongoing) {
            onGameEnd(viewModel.generateLog())
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        HeaderBar(isPlayerTurn, timeElapsed, hasTime, maxTime)
        Spacer(Modifier.height(16.dp))

        // Board Grid
        BoxWithConstraints {
            val cellWidth = maxWidth / size
            Column {
                for (r in 0 until size) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        for (c in 0 until size) {
                            BoardCell(
                                state = board.grid[r][c],
                                size = cellWidth,
                                onClick = { viewModel.playTurn(c) }
                            )
                        }
                    }
                }
            }
        }
    }
}