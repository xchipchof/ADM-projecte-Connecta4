package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.JocScreen
import udl.adm.connecta4.viewmodel.GameViewModel
import kotlin.properties.Delegates

class JocActivity : ComponentActivity() {

    private lateinit var a :String
    private var s by Delegates.notNull<Int>()
    private var h by Delegates.notNull<Boolean>()
    private var m by Delegates.notNull<Int>()

    private val jocViewModel : GameViewModel by viewModels {
        GameViewModel.Factory(a,s,h,m)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        a = intent.getStringExtra("ALIAS") ?: "p1"
        s = intent.getIntExtra("SIZE", 7)
        h = intent.getBooleanExtra("HAS_TIME", false)
        m = intent.getIntExtra("MAX_TIME", 25)

        setContent {
            Connecta4Theme() {
                JocScreen(
                    viewModel = jocViewModel,
                    onGameEnd = { log ->
                        val intent = Intent(this, ResultatsActivity::class.java).apply {
                            putExtra("LOG_DATA", log)
                        }
                        startActivity(intent)
                        finish()
                    }
                )
            }
        }
    }
}