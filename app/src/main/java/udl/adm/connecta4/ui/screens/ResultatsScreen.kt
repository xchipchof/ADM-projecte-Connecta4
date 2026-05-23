package udl.adm.connecta4.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import udl.adm.connecta4.ui.BoardBlue
import udl.adm.connecta4.ui.NavyDeep
import udl.adm.connecta4.viewmodel.ResultatsViewModel

@Composable
fun ResultatsScreen(
    resViewModel: ResultatsViewModel,
    log: String,
    onConfigClick: () -> Unit,
    onNewGame: () -> Unit,
    onExit: () -> Unit,
) {
    ResultatsContent(
        log          = log,
        email        = resViewModel.email,
        dateTime     = resViewModel.dateTime,
        emailChanger = { resViewModel.onEmailChange(it) },
        onConfigClick = onConfigClick,
        onNewGame    = onNewGame,
        onExit       = onExit
    ) { email: String, dateTime: String, context: Context ->
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:".toUri()
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Log - $dateTime")
                putExtra(Intent.EXTRA_TEXT, log)
            }
            context.startActivity(Intent.createChooser(intent, "Enviar correu"))
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
    onConfigClick: () -> Unit,
    onNewGame: () -> Unit,
    onExit: () -> Unit,
    emailSender: (String, String, Context) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDeep)
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "RESULTATS",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight    = FontWeight.Black,
                        letterSpacing = 4.sp,
                        color         = MaterialTheme.colorScheme.onBackground
                    )
                )
                Text(
                    "Resum de la partida",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color         = MaterialTheme.colorScheme.onSurfaceVariant,
                        letterSpacing = 1.sp
                    )
                )
            }
            IconButton(onClick = onConfigClick) {
                Icon(
                    imageVector     = Icons.Filled.Settings,
                    contentDescription = "Preferències",
                    tint            = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        ResultsCard(dateTime, log)

        Spacer(Modifier.height(12.dp))

        EmailCard(email, emailChanger, emailSender, dateTime, context)

        Spacer(Modifier.weight(1f))

        Button(
            onClick  = onNewGame,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape  = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BoardBlue,
                contentColor   = Color.White
            )
        ) {
            Text(
                "NOVA PARTIDA",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
        }

        Spacer(Modifier.height(10.dp))

        Button(
            onClick  = onExit,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape  = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error.copy(alpha = 0.85f),
                contentColor   = Color.White
            )
        ) {
            Text(
                "SORTIR",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun ResultsCard(dateTime: String, log: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionLabelR("DETALLS DE LA PARTIDA")
            Spacer(Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Data i hora:",
                    style    = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.width(100.dp)
                )
                Text(
                    dateTime,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color      = MaterialTheme.colorScheme.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Spacer(Modifier.height(12.dp))
            SectionLabelR("LOG DE LA PARTIDA")
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value         = log,
                onValueChange = {},
                readOnly      = true,
                modifier      = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                shape  = RoundedCornerShape(10.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                )
            )
        }
    }
}

@Composable
private fun EmailCard(
    email: String,
    emailChanger: (String) -> Unit,
    emailSender: (String, String, Context) -> Unit,
    dateTime: String,
    context: Context
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionLabelR("ENVIAR PER CORREU")
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value         = email,
                onValueChange = emailChanger,
                label         = { Text("Correu destinatari") },
                singleLine    = true,
                modifier      = Modifier.fillMaxWidth(),
                shape         = RoundedCornerShape(10.dp),
                colors        = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor   = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                )
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick  = { emailSender(email, dateTime, context) },
                modifier = Modifier.fillMaxWidth(),
                shape    = RoundedCornerShape(10.dp),
                colors   = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.85f),
                    contentColor   = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    "ENVIAR E-MAIL",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight    = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                )
            }
        }
    }
}

@Composable
private fun SectionLabelR(text: String) {
    Text(
        text  = text,
        style = MaterialTheme.typography.labelSmall.copy(
            color         = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp,
            fontWeight    = FontWeight.Bold
        )
    )
}
