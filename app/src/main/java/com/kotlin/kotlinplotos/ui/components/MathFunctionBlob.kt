package com.kotlin.kotlinplotos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kotlin.kotlinplotos.R

@Composable
fun MathFunctionBlob(

) {
    Box(
        modifier = Modifier
            .width(150.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = androidx.compose.ui.graphics.Color.hsl(
                    0f,
                    0f,
                    0.78f,
                    0.3f
                ),
            ),
        contentAlignment = androidx.compose.ui.Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(R.drawable.wwf),
            contentDescription = null,
            modifier = Modifier
                .width(70.dp)
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}