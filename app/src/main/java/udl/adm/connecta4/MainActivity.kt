package udl.adm.connecta4

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.ui.screens.MenuPrincipalScreen

import udl.adm.connecta4.ui.Connecta4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Connecta4Theme {
                MenuPrincipalScreen(
                    onAjudaClick = {
                        startActivity(Intent(this, AjudaActivity::class.java))
                    },
                    onComencarClick = {
                        startActivity(Intent(this, ConfiguracioActivity::class.java))
                    },
                    onExitClick = { finishAffinity() }
                )
            }
        }
    }
}