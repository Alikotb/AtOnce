package com.example.atonce.presentation.no_internet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.theme.BoldFont

@Preview(showBackground = true)
@Composable
fun NoInternetScreen(padding: PaddingValues = PaddingValues()) {
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height((screenHeight * 0.2).dp))
        NoInternet()
        Text(
            stringResource(R.string.please_check_your_internet_connection),
            modifier = Modifier.height((screenHeight * 0.1).dp),
            fontFamily = BoldFont,
            fontSize = 16.sp
        )
        Spacer(Modifier.height((screenHeight * 0.2).dp))

    }

}