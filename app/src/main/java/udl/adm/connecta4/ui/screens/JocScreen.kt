package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import udl.adm.connecta4.model.Board
import udl.adm.connecta4.model.GameState
import udl.adm.connecta4.ui.BoardBlue
import udl.adm.connecta4.ui.BoardBlueDark
import udl.adm.connecta4.ui.NavyDeep
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
    modifier: Modifier = Modifier,
    board: Board,
    isPlayerTurn: Boolean,
    timeElapsed: Int,
    hasTime: Boolean,
    maxTime: Int,
    onCellClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(NavyDeep)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderBar(isPlayerTurn, timeElapsed, hasTime, maxTime)
            Spacer(Modifier.height(16.dp))

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                val dimPx: Dp = if (maxWidth < maxHeight) maxWidth else maxHeight
                val boardPadding = 8.dp
                val midaCasella: Dp = (dimPx - boardPadding * 2) / board.size

                Box(
                    modifier = Modifier
                        .size(dimPx)
                        .shadow(elevation = 12.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(BoardBlue, BoardBlueDark)
                            )
                        )
                        .padding(boardPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        for (r in 0 until board.size) {
                            Row(
                                modifier = Modifier.wrapContentSize(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                for (c in 0 until board.size) {
                                    BoardCell(
                                        state = board.grid[r][c],
                                        size = midaCasella,
                                        onClick = { onCellClick(c) }
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}
