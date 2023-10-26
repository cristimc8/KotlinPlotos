package com.kotlin.kotlinplotos.model.data

import com.kotlin.kotlinplotos.model.FormulaData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MathFormulasLocalSource @Inject constructor() {
    private var formulas: List<FormulaData> = listOf(
        FormulaData(
            id = 1,
            name = "Wacky Wibble",
            shortName = "WWF",
            formula = "sin(x) * cos(y) * sin(x * y)",
            mathViewFormula = "\\\\sin(x) \\\\cdot \\\\cos(y) \\\\cdot \\\\sin(x \\\\cdot y)",
        ),
        FormulaData(
            id = 2,
            name = "Funky Frizzle",
            shortName = "FFF",
            formula = "5 * sin(x) + 2 * cos(2x) + 3 * tan(0.5x) + sqrt(abs(x)) - 1 / (x^2) + log(3x, 2) - e^x",
            mathViewFormula = "5 \\\\cdot \\\\sin(x) + 2 \\\\cdot \\\\cos(2x) + 3 \\\\cdot \\\\tan(\\\\frac{1}{2}x) + \\\\sqrt{|x|} - \\\\frac{1}{x^2} + \\\\log_2(3x) - e^x"
        )
    )

    fun getFormulas(): List<FormulaData> {
        return formulas
    }
}