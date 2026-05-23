package udl.adm.connecta4.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import udl.adm.connecta4.bot.Connecta4Bot
import udl.adm.connecta4.data.GameDatabase
import udl.adm.connecta4.model.Board
import udl.adm.connecta4.model.CellState
import udl.adm.connecta4.model.GameLog
import udl.adm.connecta4.model.GameRecord
import udl.adm.connecta4.model.GameState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GameViewModel(
    application: Application,
    val alias: String,
    val size: Int,
    val hasTime: Boolean,
    val maxTime: Int
) : AndroidViewModel(application) {

    private val db         = GameDatabase.getDatabase(application)
    private val timeFmt    = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val dateFmt    = SimpleDateFormat("d/M/yy, HH:mm", Locale.getDefault())
    private val logBuilder = StringBuilder()

    var board by mutableStateOf(Board(size))
        private set

    var gameState by mutableStateOf<GameState>(GameState.Ongoing)
        private set

    var isPlayerTurn by mutableStateOf(true)
        private set

    var timeElapsed by mutableStateOf(0)
        private set

    var liveLog by mutableStateOf("")
        private set

    private var turnStartTime: String = now()

    init {
        logBuilder.append("LOG...\n")
        logBuilder.append("Alias: $alias; Mida graella: $size\n")
        logBuilder.append(if (hasTime) "Control del temps\n\n" else "Sense control del temps\n\n")
        liveLog = logBuilder.toString()

        viewModelScope.launch {
            while (gameState == GameState.Ongoing) {
                delay(1000)
                timeElapsed++
                if (hasTime && timeElapsed >= maxTime) {
                    gameState = GameState.TimeOut
                    saveGame()
                }
            }
        }
    }

    fun playTurn(col: Int) {
        if (gameState != GameState.Ongoing || !isPlayerTurn) return
        val endTime = now()

        if (board.dropPiece(col, CellState.PLAYER)) {
            board = board
            appendEntry(col, alias, turnStartTime, endTime)

            when {
                board.checkWin(CellState.PLAYER) -> { gameState = GameState.PlayerWins; saveGame() }
                board.isFull()                   -> { gameState = GameState.Draw;        saveGame() }
                else -> { isPlayerTurn = false; systemPlay() }
            }
        }
    }

    private fun systemPlay() {
        viewModelScope.launch {
            delay(600)
            val col = Connecta4Bot.selectColumn(board)
            if (col != -1 && board.dropPiece(col, CellState.SYSTEM)) {
                board = board
                appendEntry(col, "Sistema", null, null)
                turnStartTime = now()

                when {
                    board.checkWin(CellState.SYSTEM) -> { gameState = GameState.SystemWins; saveGame() }
                    board.isFull()                   -> { gameState = GameState.Draw;        saveGame() }
                    else -> isPlayerTurn = true
                }
            }
        }
    }

    private fun appendEntry(col: Int, player: String, start: String?, end: String?) {
        logBuilder.append("Columna: ${col + 1} ($player)\n")
        if (start != null && end != null) {
            logBuilder.append("  Inici: $start → Fi: $end\n")
        }
        if (hasTime) {
            logBuilder.append("  Temps restant: ${maxTime - timeElapsed}s.\n")
        }
        logBuilder.append("\n")
        liveLog = logBuilder.toString()
    }

    fun generateLog(): String {
        val remaining = if (hasTime) maxTime - timeElapsed else null
        return GameLog(alias, size, timeElapsed, remaining, gameState).buildLog()
    }

    private fun saveGame() {
        viewModelScope.launch {
            val resultStr = when (gameState) {
                is GameState.PlayerWins -> "Guanyada"
                is GameState.SystemWins -> "Perduda"
                is GameState.Draw       -> "Empat"
                is GameState.TimeOut    -> "Temps esgotat"
                else -> return@launch
            }
            db.gameDao().insert(
                GameRecord(
                    date    = dateFmt.format(Date()),
                    result  = resultStr,
                    logText = generateLog()
                )
            )
        }
    }

    private fun now(): String = timeFmt.format(Date())

    class Factory(
        private val application: Application,
        private val alias: String,
        private val size: Int,
        private val hasTime: Boolean,
        private val maxTime: Int
    ) : ViewModelProvider.AndroidViewModelFactory(application) {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return GameViewModel(application, alias, size, hasTime, maxTime) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
