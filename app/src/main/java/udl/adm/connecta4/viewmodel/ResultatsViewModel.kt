package udl.adm.connecta4.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ResultatsViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    val dateTime: String = SimpleDateFormat("d/M/yy, HH:mm", Locale.getDefault()).format(Date())

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }
}
