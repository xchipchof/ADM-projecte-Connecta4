package udl.adm.connecta4.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.ConfiguracioScreen
import udl.adm.connecta4.viewmodel.ConfiguracioViewModel

class ConfiguracioActivity : ComponentActivity() {

    private val configViewModel: ConfiguracioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Connecta4Theme {
                ConfiguracioScreen(
                    configViewModel = configViewModel,
                    onSave          = { finish() }
                )
            }
        }
    }
}
