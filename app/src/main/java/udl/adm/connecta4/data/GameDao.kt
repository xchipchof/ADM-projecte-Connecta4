package udl.adm.connecta4.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import udl.adm.connecta4.model.GameRecord

@Dao
interface GameDao {
    @Insert
    suspend fun insert(record: GameRecord)

    @Query("SELECT * FROM game_records ORDER BY id DESC")
    fun getAllRecords(): Flow<List<GameRecord>>
}
