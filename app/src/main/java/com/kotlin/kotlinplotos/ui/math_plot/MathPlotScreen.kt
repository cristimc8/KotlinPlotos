package com.kotlin.kotlinplotos.ui.math_plot

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.kotlin.kotlinplotos.ui.BooleanPreviewParameterProvider
import com.kotlin.kotlinplotos.ui.components.BlurredImageBackground
import com.kotlin.kotlinplotos.ui.components.Header
import com.kotlin.kotlinplotos.ui.components.MathFunctionBlob
import com.kotlin.kotlinplotos.ui.theme.KotlinPlotosTheme
import kotlinx.coroutines.flow.Flow


@Composable
fun MathPlotScreen(
    state: MathPlotContract.State,
    effectFlow: Flow<MathPlotContract.Effect>?,
) {

    LaunchedEffect(effectFlow) {
        effectFlow?.collect { effect: MathPlotContract.Effect ->
            when (effect) {
                is MathPlotContract.Effect.FormulaSelected -> {
                    Log.d("MathPlotScreen", "Current formula: ${state.currentFormula}")
                }
            }
        }
    }

    Container(modifier = Modifier.fillMaxSize()) {
        Header()
        MathFunctionsContainer()
    }
}

@Composable
fun Container(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
    ) {
        BlurredImageBackground()
        content()
    }
}

@Composable
fun MathFunctionsContainer(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
        ) {
            MathFunctionBlob()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(@PreviewParameter(BooleanPreviewParameterProvider::class) darkTheme: Boolean) {
    KotlinPlotosTheme(darkTheme = darkTheme) {
        Container(Modifier.fillMaxSize(), content = {
            Header()
            MathFunctionsContainer()
        })
    }
}