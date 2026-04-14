package udl.adm.connecta4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.nav.AppNavigation
import udl.adm.connecta4.ui.Connecta4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Connecta4Theme {
                AppNavigation(onExit = { finishAffinity() })
            }
        }
    }
}
