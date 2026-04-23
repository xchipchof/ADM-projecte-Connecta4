package udl.adm.connecta4

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import udl.adm.connecta4.ui.screens.JocScreen

class JocActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve data passed via Intent
        val alias = intent.getStringExtra("ALIAS") ?: "p1"
        val size = intent.getIntExtra("SIZE", 7)
        val hasTime = intent.getBooleanExtra("HAS_TIME", false)
        val maxTime = intent.getIntExtra("MAX_TIME", 25)

        setContent {
            JocScreen(
                alias = alias, size = size, hasTime = hasTime, maxTime = maxTime,
                onGameEnd = { log ->
                    val intent = Intent(this, ResultatsActivity::class.java).apply {
                        putExtra("LOG_DATA", log)
                        // Clear this activity from stack so back button doesn't return to a finished game
                        flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
                    }
                    startActivity(intent)
                    finish()
                }
            )
        }
    }
}