package com.example.atonce.presentation.splash.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atonce.R
import com.example.atonce.presentation.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun SplashScreen(viewModel: SplashViewModel = koinViewModel(), onNavToHome: () -> Unit = {}, onNavToLogIn: () -> Unit = {}) {
    val colors = MaterialTheme.colorScheme

    LaunchedEffect(Unit) {
        delay(2000)
        if (viewModel.isLoggedIn()) {
            onNavToHome()
        }else {
            onNavToLogIn()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.temp_logo),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier.size(120.dp)
        )
    }
}
