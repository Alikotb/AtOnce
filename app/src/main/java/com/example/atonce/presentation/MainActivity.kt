package com.example.atonce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.atonce.presentation.store.StoreScreen
import com.example.atonce.presentation.theme.AtOnceTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtOnceTheme {
                StoreScreen()
            }
        }
    }
}

