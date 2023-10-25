package com.kotlin.kotlinplotos.ui.math_plot

import com.kotlin.kotlinplotos.model.Formula

class MathPlotContract {
    data class State (
        val formulaList: List<Formula> = emptyList(),
        val currentFormula: Formula? = null
    )

    sealed class Effect {
        object FormulaSelected : Effect()
    }
}