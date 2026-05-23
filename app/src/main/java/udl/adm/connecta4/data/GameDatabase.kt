package udl.adm.connecta4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import udl.adm.connecta4.model.GameRecord

@Database(entities = [GameRecord::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "connecta4_db"
                ).build().also { INSTANCE = it }
            }
    }
}
