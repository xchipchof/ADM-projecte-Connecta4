package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import udl.adm.connecta4.data.UserPreferences
import udl.adm.connecta4.data.dataStore
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.ResultatsScreen
import udl.adm.connecta4.viewmodel.ResultatsViewModel

class ResultatsActivity : ComponentActivity() {

    private lateinit var il: String
    private val resViewModel: ResultatsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        il = intent.getStringExtra("LOG_DATA") ?: ""

        setContent {
            Connecta4Theme {
                ResultatsScreen(
                    resViewModel  = resViewModel,
                    log           = il,
                    onConfigClick = {
                        startActivity(Intent(this, ConfiguracioActivity::class.java))
                    },
                    onNewGame = {
                        lifecycleScope.launch {
                            val prefs   = dataStore.data.first()
                            val alias   = prefs[UserPreferences.ALIAS_KEY]     ?: "Jugador"
                            val size    = prefs[UserPreferences.GRID_SIZE_KEY] ?: 7
                            val hasTime = prefs[UserPreferences.HAS_TIME_KEY]  ?: false
                            val maxTime = prefs[UserPreferences.MAX_TIME_KEY]  ?: 25
                            startActivity(
                                Intent(this@ResultatsActivity, JocActivity::class.java).apply {
                                    putExtra("ALIAS",    alias)
                                    putExtra("SIZE",     size)
                                    putExtra("HAS_TIME", hasTime)
                                    putExtra("MAX_TIME", maxTime)
                                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                                }
                            )
                            finish()
                        }
                    },
                    onExit = { finishAffinity() }
                )
            }
        }
    }
}
