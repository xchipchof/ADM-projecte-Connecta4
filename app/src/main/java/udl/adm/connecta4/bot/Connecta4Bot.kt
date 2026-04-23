package udl.adm.connecta4.bot

import udl.adm.connecta4.model.Board
import udl.adm.connecta4.model.CellState

object Connecta4Bot {
    fun selectColumn(board: Board): Int {
        val validCols = board.getValidColumns()
        if (validCols.isEmpty()) return -1

        // 1. Can AI win?
        for (col in validCols) {
            val tempBoard = cloneBoard(board)
            tempBoard.dropPiece(col, CellState.SYSTEM)
            if (tempBoard.checkWin(CellState.SYSTEM)) return col
        }

        // 2. Can Player win? (Block)
        for (col in validCols) {
            val tempBoard = cloneBoard(board)
            tempBoard.dropPiece(col, CellState.PLAYER)
            if (tempBoard.checkWin(CellState.PLAYER)) return col
        }

        // 3. Prefer center, then random
        val center = board.size / 2
        if (validCols.contains(center)) return center
        return validCols.random()
    }

    private fun cloneBoard(board: Board): Board {
        val newBoard = Board(board.size)
        for (r in 0 until board.size) {
            for (c in 0 until board.size) {
                newBoard.grid[r][c] = board.grid[r][c]
            }
        }
        return newBoard
    }
}