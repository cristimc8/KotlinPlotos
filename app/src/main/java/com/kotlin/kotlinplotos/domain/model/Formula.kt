package com.kotlin.kotlinplotos.domain.model

data class Formula(
    val id: Number,
    val name: String,
    val formula: String,
    val mathViewFormula: String,
    val shortName: String,
) {
    override fun toString(): String {
        return "Formula(id=$id, name='$name', formula='$formula', shortName='$shortName')"
    }
}
