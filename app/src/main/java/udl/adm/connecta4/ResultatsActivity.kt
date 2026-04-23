package udl.adm.connecta4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.ui.screens.ResultatsScreen

class ResultatsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val log = intent.getStringExtra("LOG_DATA") ?: ""

        setContent {
            ResultatsScreen(
                log = log,
                onNewGame = {
                    val intent = Intent(this, ConfiguracioActivity::class.java)
                    // This flag ensures we go back to a NEW config screen and clear previous game activities
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                },
                onExit = { finishAffinity() }
            )
        }
    }
}