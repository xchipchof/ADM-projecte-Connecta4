package udl.adm.connecta4.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import udl.adm.connecta4.bot.Connecta4Bot
import udl.adm.connecta4.model.Board
import udl.adm.connecta4.model.CellState
import udl.adm.connecta4.model.GameLog
import udl.adm.connecta4.model.GameState

class GameViewModel(
    val alias: String,
    val size: Int,
    val hasTime: Boolean,
    val maxTime: Int
) : ViewModel() {


    var board by mutableStateOf(Board(size))
        private set

    var gameState by mutableStateOf<GameState>(GameState.Ongoing)
        private set

    var isPlayerTurn by mutableStateOf(true)
        private set

    var timeElapsed by mutableStateOf(0)
        private set

    init {
        viewModelScope.launch {
            while (gameState == GameState.Ongoing) {
                delay(1000)
                timeElapsed++
                if (hasTime && timeElapsed >= maxTime) {
                    gameState = GameState.TimeOut
                }
            }
        }
    }

    fun playTurn(col: Int) {
        if (gameState != GameState.Ongoing || !isPlayerTurn) return

        if (board.dropPiece(col, CellState.PLAYER)) {
            board = board

            if (board.checkWin(CellState.PLAYER)) {
                gameState = GameState.PlayerWins
            } else if (board.isFull()) {
                gameState = GameState.Draw
            } else {
                isPlayerTurn = false
                systemPlay()
            }
        }
    }

    private fun systemPlay() {
        viewModelScope.launch {
            delay(600)
            val col = Connecta4Bot.selectColumn(board)
            if (col != -1 && board.dropPiece(col, CellState.SYSTEM)) {
                board = board
                if (board.checkWin(CellState.SYSTEM)) {
                    gameState = GameState.SystemWins
                } else if (board.isFull()) {
                    gameState = GameState.Draw
                } else {
                    isPlayerTurn = true
                }
            }
        }
    }

    fun generateLog(): String {
        val remaining = if (hasTime) maxTime - timeElapsed else null
        return GameLog(alias, size, timeElapsed, remaining, gameState).buildLog()
    }

    class Factory(
        private val alias: String,
        private val size: Int,
        private val hasTime: Boolean,
        private val maxTime: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return GameViewModel(alias, size, hasTime, maxTime) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}