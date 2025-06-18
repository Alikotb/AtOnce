package com.example.atonce.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.anees.ui.navigation.SetUpNavHost
import com.example.atonce.presentation.component.navigation.CustomBottomNavBar
import com.example.atonce.presentation.theme.AtOnceTheme



class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var bottomBarState: MutableState<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AtOnceTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                navController = rememberNavController()
                bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                Scaffold(
                    bottomBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (bottomBarState.value) {
                                CustomBottomNavBar(navController = navController)
                            }
                        }
                    }
                ) { innerPadding ->
                    SetUpNavHost(
                        navController = navController,
                        bottomBarState = bottomBarState,
                        paddingValues = innerPadding
                    )
                }

            }
        }
    }
}











