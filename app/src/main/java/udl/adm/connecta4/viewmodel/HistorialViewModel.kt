package udl.adm.connecta4.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.Flow
import udl.adm.connecta4.data.GameDatabase
import udl.adm.connecta4.model.GameRecord

class HistorialViewModel(application: Application) : AndroidViewModel(application) {

    private val db = GameDatabase.getDatabase(application)

    val games: Flow<List<GameRecord>> = db.gameDao().getAllRecords()

    var selectedRecord by mutableStateOf<GameRecord?>(null)
        private set

    fun selectRecord(record: GameRecord) {
        selectedRecord = record
    }
}
