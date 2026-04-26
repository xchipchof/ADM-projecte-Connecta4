package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import udl.adm.connecta4.ui.screens.ResultatsScreen
import udl.adm.connecta4.viewmodel.ResultatsViewModel

class ResultatsActivity : ComponentActivity() {

    private lateinit var il: String

    private val resViewModel: ResultatsViewModel by viewModels { ResultatsViewModel.Factory(initLog = il) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        il = intent.getStringExtra("LOG_DATA") ?: ""

        setContent {
            ResultatsScreen(
                resViewModel = resViewModel,
                log = il,
                onNewGame = {
                    val intent = Intent(this, ConfiguracioActivity::class.java)

                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                },
                onExit = { finishAffinity() }
            )
        }
    }
}