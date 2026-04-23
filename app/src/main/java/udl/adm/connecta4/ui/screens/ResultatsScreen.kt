package udl.adm.connecta4.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ResultatsScreen(log: String, onNewGame: () -> Unit, onExit: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("professor@domain.cat") }
    val dateTime = remember { SimpleDateFormat("d/M/yy, HH:mm", Locale.getDefault()).format(Date()) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("RESULTATS PARTIDA", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = dateTime, onValueChange = {}, label = { Text("Dia i hora") }, readOnly = true, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = log, onValueChange = {}, label = { Text("Valors del Log") }, readOnly = true, modifier = Modifier.fillMaxWidth().height(150.dp))
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("E-mail destinatari") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                        putExtra(Intent.EXTRA_SUBJECT, "Log - $dateTime")
                        putExtra(Intent.EXTRA_TEXT, log)
                    }
                    context.startActivity(Intent.createChooser(intent, "Send Email"))
                } else {
                    Toast.makeText(context, "Correu invàlid", Toast.LENGTH_SHORT).show()
                }
            }
        ) { Text("Enviar e-mail") }

        Spacer(Modifier.height(8.dp))
        Button(onClick = onNewGame, modifier = Modifier.fillMaxWidth()) { Text("Nova partida") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onExit, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)) { Text("Sortir") }
    }
}