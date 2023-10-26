package com.kotlin.kotlinplotos.infra

import com.kotlin.kotlinplotos.R
import com.kotlin.kotlinplotos.model.FormulaData
import com.kotlin.kotlinplotos.ui.domain.FormulaItem

object FormulaMapper {
    fun FormulaData.toFormulaItem() = FormulaItem(
        id = id,
        name = name,
        formula = formula,
        mathViewFormula = mathViewFormula,
        shortName = shortName,
        formulaImageResource = R.drawable::class.java.getField(shortName.lowercase()).getInt(null)
    )

    fun FormulaItem.toFormulaData() = FormulaData(
        id = id,
        name = name,
        formula = formula,
        mathViewFormula = mathViewFormula,
        shortName = shortName,
    )
}