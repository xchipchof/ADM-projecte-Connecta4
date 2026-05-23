package udl.adm.connecta4.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_records")
data class GameRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val result: String,
    val logText: String
)
