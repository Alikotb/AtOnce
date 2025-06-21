package com.example.atonce.presentation.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun CustomSnackbar(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(top = 36.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF323232)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(16.dp),
                color = Color.White
            )
        }
    }

    LaunchedEffect(message) {
        delay(2500)
        onDismiss()
    }
}
