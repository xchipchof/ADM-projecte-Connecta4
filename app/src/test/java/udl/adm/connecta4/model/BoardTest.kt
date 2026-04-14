package udl.adm.connecta4.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BoardTest {

    var board: Board? = null

    @BeforeEach
    fun setUp() {
        board = Board(8)
    }

    @Test
    fun getGridTest() {
        val expectedBoard: Array<Array<CellState>> = Array(8) { Array(8) { CellState.EMPTY } }
        Assertions.assertArrayEquals(expectedBoard, board?.grid)
    }

    @Test
    fun isFullTest() {
        Assertions.assertEquals(false, board?.isFull())
        for (j in 0 until 8) {
            for (i in 0 until 8) {
                board?.dropPiece(i, CellState.PLAYER)
            }
        }

        Assertions.assertEquals(true, board?.isFull())


    }

    @Test
    fun dropPieceTest() {
        Assertions.assertEquals(true, board?.dropPiece(0, CellState.PLAYER))
        Assertions.assertEquals(CellState.PLAYER, board?.grid?.get(7)?.get(0))
        for (i in 0 until 7) {
            board?.dropPiece(0, CellState.PLAYER)
        }
        Assertions.assertEquals(false, board?.dropPiece(0, CellState.PLAYER))
    }

    @Test
    fun getValidColumnsTest() {
        Assertions.assertEquals(listOf(0, 1, 2, 3, 4, 5, 6, 7), board?.getValidColumns())
        for (i in 0 until 8) {
            board?.dropPiece(0, CellState.PLAYER)
        }
        Assertions.assertEquals(listOf(1, 2, 3, 4, 5, 6, 7), board?.getValidColumns())

    }

    @Test
    fun checkWinHorizontalTest() {
        Assertions.assertEquals(false, board?.checkWin(CellState.PLAYER))
        val player: CellState = CellState.PLAYER
        board?.dropPiece(0, player)
        board?.dropPiece(1, player)
        board?.dropPiece(2, player)
        board?.dropPiece(3, player)
        Assertions.assertEquals(true, board?.checkWin(player))
    }

    @Test
    fun checkWinVerticalTest() {
        Assertions.assertEquals(false, board?.checkWin(CellState.PLAYER))
        val player: CellState = CellState.PLAYER
        board?.dropPiece(0, player)
        board?.dropPiece(0, player)
        board?.dropPiece(0, player)
        board?.dropPiece(0, player)
        Assertions.assertEquals(true, board?.checkWin(player))
    }

    @Test
    fun checkWinDiagonalTest() {
        Assertions.assertEquals(false, board?.checkWin(CellState.PLAYER))
        val player: CellState = CellState.PLAYER
        val other: CellState = CellState.SYSTEM
        board?.dropPiece(0, player)
        board?.dropPiece(1, other)
        board?.dropPiece(1, player)
        board?.dropPiece(2, player)
        board?.dropPiece(2, other)
        board?.dropPiece(2, player)
        board?.dropPiece(3, other)
        board?.dropPiece(3, other)
        board?.dropPiece(3, other)
        board?.dropPiece(3, player)
        Assertions.assertEquals(true, board?.checkWin(player))
    }

}