package com.example.atonce.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.atonce.R


@Composable
fun NoInternet(){
    val config = LocalConfiguration.current
    val screenHeight =config.screenHeightDp
    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))
    Box{
        LottieAnimation(
            composition = composition.value,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.height((screenHeight*0.5).dp),
            renderMode = RenderMode.AUTOMATIC
        )
    }
}