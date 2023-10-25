package com.kotlin.kotlinplotos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlinplotos.R
import com.kotlin.kotlinplotos.model.Formula

@Composable
fun MathFunctionBlob(
    modifier: Modifier = Modifier,
    formula: Formula,
    onFormulaSelected: (Formula) -> Unit,
) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = androidx.compose.ui.graphics.Color.hsl(
                    0f,
                    0f,
                    0.78f,
                    0.3f
                ),
            ),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(R.drawable.wwf),
                contentDescription = null,
                modifier = Modifier
                    .width(70.dp)
                    .padding(top = 12.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = "${formula.shortName}(x, y)",
                modifier = Modifier.padding(top = 6.dp),
                style = androidx.compose.ui.text.TextStyle(
                    color = androidx.compose.ui.graphics.Color.LightGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Text(
                text = formula.name,
                modifier = Modifier.padding(top = 20.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                style = androidx.compose.ui.text.TextStyle(
                    color = androidx.compose.ui.graphics.Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                )
            )
        }

    }
}

@Preview
@Composable
fun MathFunctionBlobPreview() {
    MathFunctionBlob(
        formula = Formula.EXAMPLE_FORMULA,
        onFormulaSelected = {},
    )
}