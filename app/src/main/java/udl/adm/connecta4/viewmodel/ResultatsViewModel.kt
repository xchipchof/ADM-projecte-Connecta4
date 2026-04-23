package udl.adm.connecta4.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResultatsViewModel(val initialLog: String) : ViewModel() {
    var logText by mutableStateOf(initialLog)
        private set

    var email by mutableStateOf("")
        private set

    var dateTime by mutableStateOf(SimpleDateFormat("d/M/yy, HH:mm", Locale.getDefault()).format(Date()))
        private set

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    class Factory(private val initLog: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ResultatsViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ResultatsViewModel(initLog) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}