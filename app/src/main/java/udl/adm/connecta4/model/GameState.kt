package udl.adm.connecta4.model

sealed class GameState {
    object Ongoing : GameState()
    object PlayerWins : GameState()
    object SystemWins : GameState()
    object Draw : GameState()
    object TimeOut : GameState()
}