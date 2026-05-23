package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.JocScreen
import udl.adm.connecta4.viewmodel.GameViewModel
import kotlin.properties.Delegates

class JocActivity : ComponentActivity() {

    private lateinit var a: String
    private var s by Delegates.notNull<Int>()
    private var h by Delegates.notNull<Boolean>()
    private var m by Delegates.notNull<Int>()

    private val jocViewModel: GameViewModel by viewModels {
        GameViewModel.Factory(application, a, s, h, m)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        a = intent.getStringExtra("ALIAS") ?: "Jugador"
        s = intent.getIntExtra("SIZE", 7)
        h = intent.getBooleanExtra("HAS_TIME", false)
        m = intent.getIntExtra("MAX_TIME", 25)

        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        setContent {
            Connecta4Theme {
                val windowSizeClass = calculateWindowSizeClass(this)
                JocScreen(
                    viewModel = jocViewModel,
                    windowSizeClass = windowSizeClass,
                    onGameEnd = { log ->
                        startActivity(
                            Intent(this, ResultatsActivity::class.java).apply {
                                putExtra("LOG_DATA", log)
                            }
                        )
                        finish()
                    }
                )
            }
        }
    }
}
