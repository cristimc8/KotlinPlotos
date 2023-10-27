package com.kotlin.kotlinplotos.ui.math_plot

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlinplotos.model.FormulaData
import com.kotlin.kotlinplotos.model.data.MathFormulasLocalSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.mariuszgromada.math.mxparser.Argument
import org.mariuszgromada.math.mxparser.Expression
import javax.inject.Inject

@HiltViewModel
class MathPlotViewModel @Inject constructor(
    private val localSource: MathFormulasLocalSource
) : ViewModel() {

    var state by mutableStateOf(
        MathPlotContract.State(
            formulaList = listOf(),
            currentFormula = null,
            computing = false,
            resultX = listOf(),
            resultY = listOf(),
        )
    )
        private set

    var effects = Channel<MathPlotContract.Effect>(Channel.UNLIMITED)
        private set

    init {
        loadFormulas()
    }

    private fun loadFormulas() {
        state = state.copy(
            formulaList = localSource.getFormulas()
        )
    }

    /**
     * Called when the user selects a formula from the list.
     */
    fun onFormulaSelected(formula: FormulaData) {
        state = state.copy(
            currentFormula = formula
        )
        viewModelScope.launch {
            effects.send(MathPlotContract.Effect.FormulaSelected)
            if (state.computing) {
                computeResult()
            }
        }
    }

    /**
     * Called when the user clicks on the "Compute" button.
     */
    fun onComputing() {
        state = state.copy(
            computing = true
        )
        viewModelScope.launch {
            computeResult()
        }
    }

    /**
     * Computes the result of the current formula.
     */
    private suspend fun computeResult() {
        clearPreviousResults()

        val currentFormula = state.currentFormula ?: return
        val expr = currentFormula.formula

        // evaluate on different thread
        withContext(Dispatchers.Default) {
            evaluateForInterval(expr)
        }
    }

    private fun clearPreviousResults() {
        state = state.copy(
            resultX = listOf(),
            resultY = listOf(),
        )
    }

    private fun evaluateForInterval(expr: String) {
        val xValues = mutableListOf<Double>()
        val yValues = mutableListOf<Double>()
        var x = INTERVAL_START
        while (x <= INTERVAL_END) {
            val y = Expression(
                expr,
                Argument("x", x),
                Argument("y", 0 - x),
            ).calculate()

            // if y is not a real number, skip it
            if (notValidResult(y)) {
                x += INTERVAL_STEP
                continue
            }

            xValues.add(x)
            yValues.add(y)
            x += INTERVAL_STEP
        }

        Log.d("MathPlotViewModel", "xValues: $xValues")
        Log.d("MathPlotViewModel", "yValues: $yValues")
        state = state.copy(
            resultX = xValues,
            resultY = yValues,
        )

    }

    private fun notValidResult(result: Double): Boolean {
        return result.isNaN() || result.isInfinite()
    }

    companion object {
        val INTERVAL_START = -10.0
        val INTERVAL_END = 10.0
        val INTERVAL_STEP = 0.1
    }
}