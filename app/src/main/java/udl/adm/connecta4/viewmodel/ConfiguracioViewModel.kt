package udl.adm.connecta4.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ConfiguracioViewModel : ViewModel() {
    var alias by mutableStateOf("p1")
        private set

    var size by mutableStateOf(7)
        private set

    var hasTime by mutableStateOf(false)
        private set

    var maxTime by mutableStateOf("25")
        private set

    fun onAliasChange(newAlias: String) { alias = newAlias }
    fun onSizeChange(newSize: Int) { size = newSize }
    fun onHasTimeChange(checked: Boolean) { hasTime = checked }
    fun onMaxTimeChange(newTime: String) { maxTime = newTime }
}