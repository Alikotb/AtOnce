package com.example.atonce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.atonce.presentation.cart.CartScreen
import com.example.atonce.presentation.home.HomeScreen
import com.example.atonce.presentation.login.LoginScreen
import com.example.atonce.presentation.orders.OrderScreen
import com.example.atonce.presentation.signup.SignUpScreen
import com.example.atonce.presentation.store.StoreScreen
import com.example.atonce.presentation.theme.AtOnceTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtOnceTheme {
//            LoginScreen()
//                StoreScreen()
//            OrderScreen()
//                SignUpScreen()
//                 HomeScreen()
                CartScreen()
            }
        }
    }
}

