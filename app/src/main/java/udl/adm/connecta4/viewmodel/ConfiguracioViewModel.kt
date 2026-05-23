package udl.adm.connecta4.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import udl.adm.connecta4.data.UserPreferences
import udl.adm.connecta4.data.dataStore

class ConfiguracioViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = application.dataStore

    var alias by mutableStateOf("Jugador")
        private set

    var size by mutableStateOf(7)
        private set

    var hasTime by mutableStateOf(false)
        private set

    var maxTime by mutableStateOf("25")
        private set

    init {
        viewModelScope.launch {
            val prefs = dataStore.data.first()
            alias   = prefs[UserPreferences.ALIAS_KEY]     ?: "Jugador"
            size    = prefs[UserPreferences.GRID_SIZE_KEY] ?: 7
            hasTime = prefs[UserPreferences.HAS_TIME_KEY]  ?: false
            maxTime = (prefs[UserPreferences.MAX_TIME_KEY] ?: 25).toString()
        }
    }

    fun onAliasChange(newAlias: String)   { alias   = newAlias   }
    fun onSizeChange(newSize: Int)         { size    = newSize    }
    fun onHasTimeChange(checked: Boolean) { hasTime = checked    }
    fun onMaxTimeChange(newTime: String)  { maxTime = newTime    }

    fun savePreferences(onSaved: () -> Unit) {
        viewModelScope.launch {
            dataStore.edit { prefs ->
                prefs[UserPreferences.ALIAS_KEY]     = alias.ifBlank { "Jugador" }
                prefs[UserPreferences.GRID_SIZE_KEY] = size
                prefs[UserPreferences.HAS_TIME_KEY]  = hasTime
                prefs[UserPreferences.MAX_TIME_KEY]  = maxTime.toIntOrNull() ?: 25
            }
            onSaved()
        }
    }
}
