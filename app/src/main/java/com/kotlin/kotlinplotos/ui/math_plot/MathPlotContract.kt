package com.kotlin.kotlinplotos.ui.math_plot

import com.kotlin.kotlinplotos.model.FormulaData

class MathPlotContract {
    data class State (
        val formulaList: List<FormulaData> = emptyList(),
        val currentFormula: FormulaData? = null
    )

    sealed class Effect {
        object FormulaSelected : Effect()
    }
}
