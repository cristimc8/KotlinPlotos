package com.kotlin.kotlinplotos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlinplotos.R
import com.kotlin.kotlinplotos.ui.theme.latoFamily

@Composable
fun Header(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HeaderText(modifier = modifier)
            AppLogo()
        }
    }
}

@Composable
fun HeaderText(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.then(
            Modifier.fillMaxWidth(),
        ),
        contentAlignment = Alignment.TopCenter,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 26.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "Plotos, ",
                modifier = modifier,
                fontFamily = latoFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 26.sp,
            )
            Text(
                text = "Math Inc.",
                modifier = modifier,
                fontFamily = latoFamily,
                fontWeight = FontWeight.SemiBold,
                color = androidx.compose.ui.graphics.Color(0xFFACACAC),
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun AppLogo() {
    Box(
        modifier = Modifier
            .width(120.dp)
            .then(
                Modifier.padding(top = 20.dp),
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.mathtree),
            contentDescription = "Logo",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
        )
    }
}
