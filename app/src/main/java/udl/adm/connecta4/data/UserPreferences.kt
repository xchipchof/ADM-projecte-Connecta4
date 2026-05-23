package udl.adm.connecta4.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

object UserPreferences {
    val ALIAS_KEY = stringPreferencesKey("alias")
    val GRID_SIZE_KEY = intPreferencesKey("grid_size")
    val HAS_TIME_KEY = booleanPreferencesKey("has_time")
    val MAX_TIME_KEY = intPreferencesKey("max_time")
}
