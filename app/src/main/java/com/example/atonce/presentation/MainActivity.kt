package com.example.atonce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.anees.ui.navigation.SetUpNavHost
import com.example.atonce.presentation.theme.AtOnceTheme



class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtOnceTheme {
                navController = rememberNavController()

                Scaffold { innerPadding ->
                    SetUpNavHost(
                        navController = navController,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

