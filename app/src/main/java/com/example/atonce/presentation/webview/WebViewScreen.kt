package com.example.atonce.presentation.webview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.common.component.ProgressIndicator
import com.example.atonce.presentation.common.component.app_bar_cards.OneIconCard
import com.example.atonce.presentation.common.theme.BlackColor
import com.example.atonce.presentation.common.theme.WhiteColor
import com.example.atonce.presentation.webview.components.WebViewContainer

@Composable
fun WebViewScreen(
    title: String,
    url: String,
    onBackClick: () -> Unit,
    padding: PaddingValues
) {
    var isLoading by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().padding(top = padding.calculateTopPadding())) {
        OneIconCard(
            onClick = { onBackClick() },
            headerTxt = title
        )

        Box(modifier = Modifier.fillMaxSize()) {
            WebViewContainer(url = url, isLoadingState = { isLoading = it })

            if (isLoading) {
                ProgressIndicator()
            }
        }
    }
}

@Composable
fun WebViewTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteColor)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = BlackColor,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )

        Text(
            text = title,
            color = BlackColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.width(24.dp))
    }
}





