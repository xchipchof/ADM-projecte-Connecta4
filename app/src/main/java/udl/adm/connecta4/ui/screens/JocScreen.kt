package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import udl.adm.connecta4.model.Board
import udl.adm.connecta4.model.GameState
import udl.adm.connecta4.ui.components.BoardCell
import udl.adm.connecta4.ui.components.HeaderBar
import udl.adm.connecta4.viewmodel.GameViewModel

@Composable
fun JocScreen(
    viewModel: GameViewModel,
    onGameEnd: (String) -> Unit
) {
    val gameState = viewModel.gameState

    LaunchedEffect(gameState) {
        if (gameState != GameState.Ongoing) {
            onGameEnd(viewModel.generateLog())
        }
    }

    JocContent(
        board = viewModel.board,
        isPlayerTurn = viewModel.isPlayerTurn,
        timeElapsed = viewModel.timeElapsed,
        hasTime = viewModel.hasTime,
        maxTime = viewModel.maxTime,
        onCellClick = { col -> viewModel.playTurn(col) }
    )
}

@Composable
fun JocContent(
    board: Board,
    isPlayerTurn: Boolean,
    timeElapsed: Int,
    hasTime: Boolean,
    maxTime: Int,
    onCellClick: (Int) -> Unit
) {
    // STATELESS: No logic, just UI
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        HeaderBar(isPlayerTurn, timeElapsed, hasTime, maxTime)
        Spacer(Modifier.height(16.dp))

        BoxWithConstraints {
            val cellWidth = maxWidth / board.size
            Column {
                for (r in 0 until board.size) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        for (c in 0 until board.size) {
                            BoardCell(
                                state = board.grid[r][c],
                                size = cellWidth,
                                onClick = { onCellClick(c) }
                            )
                        }
                    }
                }
            }
        }
    }
}