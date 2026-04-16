package udl.adm.connecta4.nav

sealed class Screen {
    object Menu : Screen()
    object Ajuda : Screen()
    object Configuracio : Screen()
    data class Joc(
        val alias: String,
        val size: Int,
        val hasTime: Boolean,
        val maxTime: Int
    ) : Screen()
    data class Resultats(val log: String) : Screen()
}