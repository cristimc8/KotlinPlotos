package com.kotlin.kotlinplotos.ui.math_plot

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.kotlin.kotlinplotos.infra.FormulaMapper.toFormulaItem
import com.kotlin.kotlinplotos.model.FormulaData
import com.kotlin.kotlinplotos.ui.BooleanPreviewParameterProvider
import com.kotlin.kotlinplotos.ui.components.BlurredImageBackground
import com.kotlin.kotlinplotos.ui.components.Header
import com.kotlin.kotlinplotos.ui.components.LaTeXView
import com.kotlin.kotlinplotos.ui.components.MathFunctionBlob
import com.kotlin.kotlinplotos.ui.theme.KotlinPlotosTheme
import kotlinx.coroutines.flow.receiveAsFlow


@Composable
fun MathPlotScreen(
    viewModel: MathPlotViewModel,
) {

    val state = viewModel.state
    val effectFlow = viewModel.effects.receiveAsFlow()

    LaunchedEffect(effectFlow) {
        effectFlow.collect { effect: MathPlotContract.Effect ->
            when (effect) {
                is MathPlotContract.Effect.FormulaSelected -> {
                    Log.d("MathPlotScreen", "Current formula: ${viewModel.state.currentFormula}")
                }
            }
        }
    }

    Container(modifier = Modifier.fillMaxSize()) {
        Header()
        if (state.currentFormula != null) {
            LatexFormulaDisplayContainer(
                formula = state.currentFormula,
            )
        }
        MathFunctionsContainer(
            formulas = state.formulaList,
            currentFormula = state.currentFormula,
            onFormulaSelected = { formula ->
                viewModel.onFormulaSelected(formula)
            }
        )
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
fun MathFunctionsContainer(
    modifier: Modifier = Modifier,
    formulas: List<FormulaData> = listOf(),
    currentFormula: FormulaData?,
    onFormulaSelected: (FormulaData) -> Unit = {},
) {
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
            // foreach formula render a blob
            formulas.forEach { formula ->
                MathFunctionBlob(
                    modifier = Modifier
                        .padding(8.dp),
                    formula = formula.toFormulaItem(),
                    onFormulaSelected = onFormulaSelected,
                    selected = formula == currentFormula,
                )
            }
        }
    }
}

@Composable
fun LatexFormulaDisplayContainer(
    formula: FormulaData,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LaTeXView(
                latex = formula.mathViewFormula,
            )

            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Plot ${formula.shortName}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) darkTheme: Boolean
) {
    KotlinPlotosTheme(darkTheme = darkTheme) {
        Container(Modifier.fillMaxSize(), content = {
            Header()
            MathFunctionsContainer(
                formulas = listOf(FormulaData.EXAMPLE_FORMULA),
                currentFormula = FormulaData.EXAMPLE_FORMULA,
            )
        })
    }
}