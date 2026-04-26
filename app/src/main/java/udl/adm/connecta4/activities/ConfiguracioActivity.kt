package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.ConfiguracioScreen


class ConfiguracioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Connecta4Theme {
                ConfiguracioScreen(
                    configViewModel = viewModel(),
                    onStartGame = { alias, size, hasTime, maxTime ->
                        val intent = Intent(this, JocActivity::class.java).apply {
                            putExtra("ALIAS", alias)
                            putExtra("SIZE", size)
                            putExtra("HAS_TIME", hasTime)
                            putExtra("MAX_TIME", maxTime)
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}