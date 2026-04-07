package udl.adm.Connecta4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.Connecta4.nav.AppNavigation
import udl.adm.Connecta4.ui.theme.Connecta4Theme

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
