package com.kotlin.kotlinplotos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlin.kotlinplotos.ui.components.BlurredImageBackground
import com.kotlin.kotlinplotos.ui.components.Header
import com.kotlin.kotlinplotos.ui.components.MathFunctionBlob
import com.kotlin.kotlinplotos.ui.math_plot.MathPlotScreen
import com.kotlin.kotlinplotos.ui.math_plot.MathPlotViewModel
import com.kotlin.kotlinplotos.ui.theme.KotlinPlotosTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow

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
            KotlinPlotosTheme {
                MathPlotApp()
            }
        }
    }
}

@Composable
fun MathPlotApp() {
    val mathPlotViewModel: MathPlotViewModel = viewModel()

    MathPlotScreen(
        state = mathPlotViewModel.state,
        effectFlow = mathPlotViewModel.effects.receiveAsFlow(),
    )
}



