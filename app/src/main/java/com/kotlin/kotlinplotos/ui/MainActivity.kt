package com.kotlin.kotlinplotos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlin.kotlinplotos.ui.math_plot.MathPlotScreen
import com.kotlin.kotlinplotos.ui.math_plot.MathPlotViewModel
import com.kotlin.kotlinplotos.ui.theme.KotlinPlotosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        setContent {
            KotlinPlotosTheme(darkTheme = true) {
                MathPlotApp()
            }
        }
    }
}

@Composable
fun MathPlotApp() {
    val mathPlotViewModel: MathPlotViewModel = viewModel()

    MathPlotScreen(
        viewModel = mathPlotViewModel,
    )
}
