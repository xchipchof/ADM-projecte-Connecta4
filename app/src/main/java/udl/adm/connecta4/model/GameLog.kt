package udl.adm.connecta4.model

class GameLog(
    private val alias: String,
    private val size: Int,
    private val timeTotal: Int,
    private val timeRemaining: Int?,
    private val result: GameState
) {
    fun buildLog(): String {
        val sb = StringBuilder()
        sb.append("Alias: $alias\nMida graella: $size\nTemps Total: $timeTotal secs.\n\n")

        when (result) {
            is GameState.PlayerWins -> {
                sb.append("Has guanyat !!\n")
                if (timeRemaining != null) sb.append("Han sobrat $timeRemaining secs. !")
            }
            is GameState.SystemWins -> sb.append("Has perdut !!\n")
            is GameState.Draw -> sb.append("Heu empatat !!\n")
            is GameState.TimeOut -> sb.append("Has esgotat el temps!!\n")
            else -> {}
        }
        return sb.toString()
    }
}