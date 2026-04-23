package udl.adm.connecta4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _board = MutableStateFlow(Board(size))
    val board: StateFlow<Board> = _board

    private val _gameState = MutableStateFlow<GameState>(GameState.Ongoing)
    val gameState: StateFlow<GameState> = _gameState

    private val _isPlayerTurn = MutableStateFlow(true)
    val isPlayerTurn: StateFlow<Boolean> = _isPlayerTurn

    private val _timeElapsed = MutableStateFlow(0)
    val timeElapsed: StateFlow<Int> = _timeElapsed

    private var timerJob: Job? = null

    init {
        startTimer()
    }

    private fun startTimer() {
        if (!hasTime && _gameState.value == GameState.Ongoing) {
            timerJob = viewModelScope.launch {
                while (true) {
                    delay(1000)
                    _timeElapsed.value++
                }
            }
        } else if (hasTime && _gameState.value == GameState.Ongoing) {
            timerJob = viewModelScope.launch {
                while (_timeElapsed.value < maxTime) {
                    delay(1000)
                    _timeElapsed.value++
                }
                endGame(GameState.TimeOut)
            }
        }
    }

    fun playTurn(col: Int) {
        if (_gameState.value != GameState.Ongoing || !_isPlayerTurn.value) return

        val b = _board.value
        if (b.dropPiece(col, CellState.PLAYER)) {
            _board.value = Board(size).apply {
                for (r in 0 until size) for (c in 0 until size) grid[r][c] = b.grid[r][c]
            }
            if (b.checkWin(CellState.PLAYER)) {
                endGame(GameState.PlayerWins)
            } else if (b.isFull()) {
                endGame(GameState.Draw)
            } else {
                _isPlayerTurn.value = false
                systemPlay()
            }
        }
    }

    private fun systemPlay() {
        viewModelScope.launch {
            delay(500) // Simulate "thinking" time
            val b = _board.value
            val col = Connecta4Bot.selectColumn(b)
            if (col != -1 && b.dropPiece(col, CellState.SYSTEM)) {
                _board.value = Board(size).apply {
                    for (r in 0 until size) for (c in 0 until size) grid[r][c] = b.grid[r][c]
                }
                if (b.checkWin(CellState.SYSTEM)) {
                    endGame(GameState.SystemWins)
                } else if (b.isFull()) {
                    endGame(GameState.Draw)
                } else {
                    _isPlayerTurn.value = true
                }
            }
        }
    }

    private fun endGame(state: GameState) {
        _gameState.value = state
        timerJob?.cancel()
    }

    fun generateLog(): String {
        val remaining = if (hasTime) maxTime - _timeElapsed.value else null
        return GameLog(alias, size, _timeElapsed.value, remaining, _gameState.value).buildLog()
    }
}