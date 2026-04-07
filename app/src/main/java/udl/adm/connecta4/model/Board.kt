package udl.adm.connecta4.model

class Board(val size: Int) {
    val grid: Array<Array<CellState>> = Array(size) { Array(size) { CellState.EMPTY } }

    fun isFull(): Boolean {
        return grid[0].none { it == CellState.EMPTY }
    }

    // TODO: Implementar la lógica per plenar les caselles i comprovar si s'ha guanyat
}