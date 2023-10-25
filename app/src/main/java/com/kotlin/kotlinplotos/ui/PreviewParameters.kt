package com.kotlin.kotlinplotos.ui

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BooleanPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true)
    override val count: Int
        get() = values.count()
}