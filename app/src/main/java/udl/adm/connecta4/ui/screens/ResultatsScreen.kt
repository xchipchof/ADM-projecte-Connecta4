package udl.adm.connecta4.ui.screens

import android.content.Context
import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import udl.adm.connecta4.viewmodel.ResultatsViewModel

@Composable
fun ResultatsScreen(
    resViewModel: ResultatsViewModel,
    log: String,
    onNewGame: () -> Unit,
    onExit: () -> Unit,
) {


    ResultatsContent(
        log = log, email = resViewModel.email, dateTime = resViewModel.dateTime,
        emailChanger = { resViewModel.onEmailChange(it) }, onNewGame = onNewGame, onExit = onExit
    )
    { email: String, dateTime: String, context: Context ->
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Log - $dateTime")
                putExtra(Intent.EXTRA_TEXT, log)
            }
            context.startActivity(Intent.createChooser(intent, "Send Email"))
        } else {
            Toast.makeText(context, "Correu invàlid", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun ResultatsContent(
    log: String,
    email: String,
    dateTime: String,
    emailChanger: (String) -> Unit,
    onNewGame: () -> Unit,
    onExit: () -> Unit,
    emailSender: (String, String, Context) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ResultsData(dateTime, log)
        OutlinedTextField(
            value = email,
            onValueChange = emailChanger,
            label = { Text("E-mail destinatari") },
            modifier = Modifier.fillMaxWidth()
        )
        SendEmailButton(emailSender, email, dateTime, context)

        NewGameButton(onNewGame)
        ExitButton(onExit)
    }
}

@Composable
private fun ColumnScope.SendEmailButton(
    emailSender: (String, String, Context) -> Unit,
    email: String,
    dateTime: String,
    context: Context
) {
    Spacer(Modifier.weight(1f))

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { emailSender(email, dateTime, context) }
    ) { Text("Enviar e-mail") }
}

@Composable
private fun NewGameButton(onNewGame: () -> Unit) {
    Spacer(Modifier.height(8.dp))
    Button(onClick = onNewGame, modifier = Modifier.fillMaxWidth()) { Text("Nova partida") }
}

@Composable
private fun ExitButton(onExit: () -> Unit) {
    Spacer(Modifier.height(8.dp))
    Button(
        onClick = onExit,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
    ) { Text("Sortir") }
}

@Composable
private fun ResultsData(dateTime: String, log: String) {
    Text("RESULTATS PARTIDA", style = MaterialTheme.typography.headlineMedium)
    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
        value = dateTime,
        onValueChange = {},
        label = { Text("Dia i hora") },
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(Modifier.height(8.dp))

    OutlinedTextField(
        value = log,
        onValueChange = {},
        label = { Text("Valors del Log") },
        readOnly = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    )
    Spacer(Modifier.height(8.dp))
}