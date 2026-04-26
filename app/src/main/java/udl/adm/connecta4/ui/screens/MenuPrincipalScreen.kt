package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MenuPrincipalScreen(
    onAjudaClick: () -> Unit,
    onComencarClick: () -> Unit,
    onExitClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(1.5f)
            .aspectRatio(0.5f)
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "CONNECT 4", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onAjudaClick) { Text("Ajuda") }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onComencarClick) { Text("Començar Partida") }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onExitClick) { Text("Sortir") }
    }
}



@Preview(showBackground = true, heightDp = 200)
@Composable
fun MenuPrincipalScreenPreview() {
    MenuPrincipalScreen({}, {}, {})
}