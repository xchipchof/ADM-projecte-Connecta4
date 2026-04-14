package udl.adm.connecta4.model

class Board(val size: Int) {
    val grid: Array<Array<CellState>> = Array(size) { Array(size) { CellState.EMPTY } }

    fun isFull(): Boolean {
        return grid[0].none { it == CellState.EMPTY }
    }

    fun dropPiece(col: Int, state: CellState): Boolean {
        for (row in size - 1 downTo 0) {
            if (grid[row][col] == CellState.EMPTY) {
                grid[row][col] = state
                return true
            }
        }
        return false
    }


    fun getValidColumns(): List<Int> {
        val validCols = mutableListOf<Int>()
        for (col in 0 until size) {
            if (grid[0][col] == CellState.EMPTY) validCols.add(col)
        }
        return validCols
    }

    fun checkWin(player: CellState): Boolean {
        for (r in 0 until size) {
            for (c in 0 until size) {
                if (grid[r][c] != player) continue
                if (c + 3 < size && grid[r][c+1] == player && grid[r][c+2] == player && grid[r][c+3] == player) return true
                if (r + 3 < size && grid[r+1][c] == player && grid[r+2][c] == player && grid[r+3][c] == player) return true
                if (r + 3 < size && c + 3 < size && grid[r+1][c+1] == player && grid[r+2][c+2] == player && grid[r+3][c+3] == player) return true
                if (r + 3 < size && c - 3 >= 0 && grid[r+1][c-1] == player && grid[r+2][c-2] == player && grid[r+3][c-3] == player) return true
            }
        }
        return false
    }

}