package udl.adm.connecta4.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.ResultatDetailScreen

class ResultatDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val logText = intent.getStringExtra("LOG_TEXT") ?: ""
        val date    = intent.getStringExtra("DATE")     ?: ""

        setContent {
            Connecta4Theme {
                ResultatDetailScreen(
                    logText = logText,
                    date    = date,
                    onBack  = { finish() }
                )
            }
        }
    }
}
