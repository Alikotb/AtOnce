package com.example.atonce.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.home.CircularIconButton
import com.example.atonce.presentation.theme.SemiBoldFont

@Composable
fun CartHeader(
    onCallClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularIconButton(icon = Icons.Default.Call, onClick = onCallClick, tint = Color(0xFF1A998E))
        Text(
            text = "Cart",
            fontFamily = SemiBoldFont,
            fontSize = 22.sp,
            color = Color(0xFF333333)
        )
        CircularIconButton(icon = Icons.Default.Person, onClick = onProfileClick, tint = Color(0xFF1A998E))
    }
}