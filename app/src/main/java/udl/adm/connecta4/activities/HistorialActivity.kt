package udl.adm.connecta4.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import udl.adm.connecta4.ui.Connecta4Theme
import udl.adm.connecta4.ui.screens.HistorialScreen
import udl.adm.connecta4.viewmodel.HistorialViewModel

class HistorialActivity : ComponentActivity() {

    private val historialViewModel: HistorialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        setContent {
            Connecta4Theme {
                val windowSizeClass = calculateWindowSizeClass(this)
                HistorialScreen(
                    viewModel = historialViewModel,
                    windowSizeClass = windowSizeClass,
                    onRecordClick = { record ->
                        startActivity(
                            Intent(this, ResultatDetailActivity::class.java).apply {
                                putExtra("LOG_TEXT", record.logText)
                                putExtra("DATE", record.date)
                            }
                        )
                    },
                    onBack = { finish() }
                )
            }
        }
    }
}
