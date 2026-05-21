package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import udl.adm.connecta4.ui.BoardBlue
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.NavyDeep
import udl.adm.connecta4.viewmodel.ConfiguracioViewModel


@Composable
fun ConfiguracioScreen(configViewModel: ConfiguracioViewModel, onStartGame: (String, Int, Boolean, Int) -> Unit) {
    ConfiguracioContent(
        alias = configViewModel.alias,
        size = configViewModel.size,
        hasTime = configViewModel.hasTime,
        maxTime = configViewModel.maxTime,
        aliasChanger = { configViewModel.onAliasChange(it) },
        sizeChanger = { configViewModel.onSizeChange(it) },
        hasTimeChanger = { configViewModel.onHasTimeChange(it) },
        maxTimeChanger = { configViewModel.onMaxTimeChange(it) },
        onStartGame = onStartGame
    )
}

@Composable
fun ConfiguracioContent(
    alias: String,
    size: Int,
    hasTime: Boolean,
    maxTime: String,
    aliasChanger: (String) -> Unit,
    sizeChanger: (Int) -> Unit,
    hasTimeChanger: (Boolean) -> Unit,
    maxTimeChanger: (String) -> Unit,
    onStartGame: (String, Int, Boolean, Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NavyDeep)
            .padding(20.dp)
    ) {
        Spacer(Modifier.height(8.dp))
        Text(
            "CONFIGURACIÓ",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Black,
                letterSpacing = 4.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        Text(
            "Configura la teva partida",
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        )

        Spacer(Modifier.height(24.dp))

        ConfigCard {
            SectionLabel("NOM DEL JUGADOR")
            Spacer(Modifier.height(8.dp))
            AliasTextField(alias = alias, onValueChange = aliasChanger)
        }

        Spacer(Modifier.height(12.dp))

        ConfigCard {
            SectionLabel("MIDA DEL TAULER")
            Spacer(Modifier.height(8.dp))
            GridSizeSelectionBlock(size = size, onValueChange = sizeChanger)
        }

        Spacer(Modifier.height(12.dp))

        ConfigCard {
            SectionLabel("CONTROL DEL TEMPS")
            Spacer(Modifier.height(4.dp))
            TimeControlOption(hasTime, onCheckedChange = hasTimeChanger)
            if (hasTime) {
                Spacer(Modifier.height(8.dp))
                TimeControlTextField(maxTime = maxTime, onValueChange = maxTimeChanger)
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                val time = maxTime.toIntOrNull() ?: 25
                onStartGame(alias.ifBlank { "Jugador" }, size, hasTime, time)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BoardBlue,
                contentColor = androidx.compose.ui.graphics.Color.White
            )
        ) {
            Text(
                "COMENÇAR PARTIDA",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
            )
        }
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun ConfigCard(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(14.dp)),
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun AliasTextField(alias: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = alias,
        onValueChange = onValueChange,
        label = { Text("El teu alias") },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
    )
}

@Composable
fun GridSizeSelectionBlock(size: Int, onValueChange: (Int) -> Unit) {
    Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
        listOf(7, 6, 5).forEach { opt ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                RadioButton(
                    selected = size == opt,
                    onClick = { onValueChange(opt) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    "${opt}×${opt}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (size == opt) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}

@Composable
fun TimeControlOption(hasTime: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = hasTime,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            "Activar límit de temps",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun TimeControlTextField(maxTime: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = maxTime,
        onValueChange = onValueChange,
        label = { Text("Límit en segons") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF08081E)
@Composable
fun GridSizeSelectionBlockPreview() {
    Connecta4Theme {
        GridSizeSelectionBlock(7) {}
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF08081E)
@Composable
fun ConfiguracioScreenPreview() {
    Connecta4Theme {
        ConfiguracioScreen(viewModel(), onStartGame = { _, _, _, _ -> })
    }
}
