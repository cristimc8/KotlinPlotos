package com.kotlin.kotlinplotos.ui.math_plot

import com.kotlin.kotlinplotos.model.FormulaData

class MathPlotContract {
    data class State(
        val formulaList: List<FormulaData> = emptyList(),
        val currentFormula: FormulaData? = null,
        val computing: Boolean = false,
        val resultX: List<Double> = emptyList(),
        val resultY: List<Double> = emptyList(),
    )

    sealed class Effect {
        object FormulaSelected : Effect()
    }
}
