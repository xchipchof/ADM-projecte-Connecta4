package udl.adm.connecta4.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.viewmodel.ConfiguracioViewModel


@Composable
fun ConfiguracioScreen(configViewModel : ConfiguracioViewModel, onStartGame: (String, Int, Boolean, Int) -> Unit) {
    var alias : String = configViewModel.alias
    var size : Int = configViewModel.size
    var hasTime : Boolean = configViewModel.hasTime
    var maxTime : String = configViewModel.maxTime

    ConfiguracioContent(
        alias,
        size,
        hasTime,
        maxTime,
        aliasChanger = { configViewModel.onAliasChange(it) },
        sizeChanger = {configViewModel.onSizeChange(it)},
        hasTimeChanger = {configViewModel.onHasTimeChange(it)},
        maxTimeChanger = {configViewModel.onMaxTimeChange(it)},
        onStartGame = onStartGame
        )

}

@Composable
fun ConfiguracioContent(
    alias: String,
    size: Int,
    hasTime: Boolean,
    maxTime: String,
    aliasChanger : (String) -> Unit,
    sizeChanger : (Int) -> Unit,
    hasTimeChanger : (Boolean) -> Unit,
    maxTimeChanger : (String) -> Unit,
    onStartGame: (String, Int, Boolean, Int) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("CONFIGURACIÓ", style = MaterialTheme.typography.headlineMedium)

        AliasTextField(alias = alias, onValueChange = aliasChanger )

        GridSizeSelectionBlock(size = size, onValueChange = sizeChanger)


        TimeControlOption(hasTime, onCheckedChange = hasTimeChanger)

        TimeControlTextField(hasTime, maxTime, onValueChange = maxTimeChanger)

        Spacer(Modifier.weight(1f))
        Button(
            onClick = {
                val time = maxTime.toIntOrNull() ?: 25
                onStartGame(alias.ifBlank { "p1" }, size, hasTime, time)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Començar")
        }
    }
}

@Composable
fun AliasTextField(alias: String, onValueChange: (String) -> Unit) {
    Spacer(Modifier.height(16.dp))

    OutlinedTextField(
        value = alias, onValueChange = onValueChange,
        label = { Text("Alias identificatiu") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun GridSizeSelectionBlock(size: Int, onValueChange: (Int) -> Unit) {
    Spacer(Modifier.height(16.dp))
    Column {
        Text("Mida graella", modifier = Modifier.padding(10.dp))
        Row {
            listOf(7, 6, 5).forEach { opt ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = size == opt, onClick = { onValueChange(opt) })
                    Text(opt.toString())
                }
            }
        }
    }
}

@Composable
fun TimeControlOption(hasTime: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Spacer(Modifier.height(16.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = hasTime, onCheckedChange = onCheckedChange)
        Text("Control del temps")
    }
}

@Composable
fun TimeControlTextField(hasTime: Boolean, maxTime: String, onValueChange: (String) -> Unit) {
    if (hasTime) {
        OutlinedTextField(
            value = maxTime, onValueChange = onValueChange,
            label = { Text("Segons") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, heightDp = 300)
@Composable
fun GridSizeSelectionBlockPreview() {
    Connecta4Theme {
        GridSizeSelectionBlock(7) {}
    }
}


@Preview(showBackground = true)
@Composable
fun ConfiguracioScreenPreview() {
    Connecta4Theme {
        ConfiguracioScreen(viewModel(),onStartGame = { _, _, _, _ -> })
    }
}