package udl.adm.connecta4.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameLogTest {

    @Test
    fun `buildLog PlayerWins with timeRemaining`() {
        val gameLog = GameLog("User", 7, 60, 15, GameState.PlayerWins)
        val expected = "Alias: User\nMida graella: 7\nTemps Total: 60 secs.\n\nHas guanyat !!\nHan sobrat 15 secs. !"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog PlayerWins without timeRemaining`() {
        val gameLog = GameLog("User", 7, 60, null, GameState.PlayerWins)
        val expected = "Alias: User\nMida graella: 7\nTemps Total: 60 secs.\n\nHas guanyat !!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog SystemWins status`() {
        val gameLog = GameLog("User", 6, 30, null, GameState.SystemWins)
        val expected = "Alias: User\nMida graella: 6\nTemps Total: 30 secs.\n\nHas perdut !!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog Draw status`() {
        val gameLog = GameLog("User", 8, 120, null, GameState.Draw)
        val expected = "Alias: User\nMida graella: 8\nTemps Total: 120 secs.\n\nHeu empatat !!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog TimeOut status`() {
        val gameLog = GameLog("User", 7, 45, 0, GameState.TimeOut)
        val expected = "Alias: User\nMida graella: 7\nTemps Total: 45 secs.\n\nHas esgotat el temps!!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog with empty alias string`() {
        val gameLog = GameLog("", 7, 60, null, GameState.Draw)
        val expected = "Alias: \nMida graella: 7\nTemps Total: 60 secs.\n\nHeu empatat !!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog with alias containing newline characters`() {
        val gameLog = GameLog("User\nName", 7, 60, null, GameState.Draw)
        val expected = "Alias: User\nName\nMida graella: 7\nTemps Total: 60 secs.\n\nHeu empatat !!\n"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog with maximum integer values`() {
        val gameLog = GameLog("User", Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, GameState.PlayerWins)
        val expected = "Alias: User\nMida graella: ${Int.MAX_VALUE}\nTemps Total: ${Int.MAX_VALUE} secs.\n\nHas guanyat !!\nHan sobrat ${Int.MAX_VALUE} secs. !"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog with zero or negative time values`() {
        val gameLog = GameLog("User", 0, -10, -5, GameState.PlayerWins)
        val expected = "Alias: User\nMida graella: 0\nTemps Total: -10 secs.\n\nHas guanyat !!\nHan sobrat -5 secs. !"
        assertEquals(expected, gameLog.buildLog())
    }

    @Test
    fun `buildLog with unhandled GameState branch`() {
        val gameLog = GameLog("User", 7, 60, null, GameState.Ongoing)
        val expected = "Alias: User\nMida graella: 7\nTemps Total: 60 secs.\n\n"
        assertEquals(expected, gameLog.buildLog())
    }

}
