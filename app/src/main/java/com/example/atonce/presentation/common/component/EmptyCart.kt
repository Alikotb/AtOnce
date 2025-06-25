package com.example.atonce.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.atonce.R
import com.example.atonce.presentation.common.theme.BoldFont
import com.example.atonce.presentation.common.theme.MediumFont


@Composable
fun EmptyCart(resId: Int = R.raw.empty_cart, messageInfo : String){
    val config = LocalConfiguration.current
    val screenHeight =config.screenHeightDp
    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(resId))
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            LottieAnimation(
                composition = composition.value,
                iterations = 1,
                modifier = Modifier.height((screenHeight*0.3).dp),
                renderMode = RenderMode.AUTOMATIC,
            )
            Text(messageInfo ,fontSize = 16.sp, fontFamily = BoldFont, textAlign = TextAlign.Center)
        }
    }
}