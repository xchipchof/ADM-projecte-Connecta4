package udl.adm.connecta4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.ui.screens.ConfiguracioScreen

class ConfiguracioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfiguracioScreen(onStartGame = { alias: String, size: Int, hasTime: Boolean, maxTime: Int ->

                val intent = Intent(this, JocActivity::class.java).apply {
                    putExtra("ALIAS", alias)
                    putExtra("SIZE", size)
                    putExtra("HAS_TIME", hasTime)
                    putExtra("MAX_TIME", maxTime)
                }
                startActivity(intent)
            })
        }
    }
}