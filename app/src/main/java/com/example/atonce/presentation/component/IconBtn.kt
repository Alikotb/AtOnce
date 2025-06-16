package com.example.atonce.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


@Composable
fun TapBarBtn( onIconClick : ()-> Unit={}, icon: ImageVector=Icons.Filled.ArrowBack){
    IconButton(onClick = {

        onIconClick()
    }) {
        Icon(
            imageVector = icon,
            contentDescription = "nill"
        )
    }
}