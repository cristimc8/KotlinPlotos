package com.kotlin.kotlinplotos.model

data class FormulaData(
    val id: Number,
    val name: String,
    val formula: String,
    val mathViewFormula: String,
    val shortName: String,
) {
    override fun toString(): String {
        return "Formula(id=$id, name='$name', formula='$formula', shortName='$shortName')"
    }

    companion object {
        val EXAMPLE_FORMULA = FormulaData(
            id = 1,
            name = "Wacky Wibble",
            shortName = "WWF",
            formula = "sin(x) * cos(y) * sin(x * y)",
            mathViewFormula = "\\sin(x) \\cdot \\cos(y) \\cdot \\sin(x \\cdot y)",
        )
    }
}
