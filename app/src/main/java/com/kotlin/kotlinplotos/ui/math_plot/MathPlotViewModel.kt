package com.kotlin.kotlinplotos.ui.math_plot

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlinplotos.model.Formula
import com.kotlin.kotlinplotos.model.data.MathFormulasLocalSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MathPlotViewModel @Inject constructor(
    private val localSource: MathFormulasLocalSource
) : ViewModel() {

    var state by mutableStateOf(
        MathPlotContract.State(
            formulaList = listOf(),
            currentFormula = null
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

    fun onFormulaSelected(formula: Formula) {
        state = state.copy(
            currentFormula = formula
        )
        viewModelScope.launch {
            effects.send(MathPlotContract.Effect.FormulaSelected)
        }
    }
}