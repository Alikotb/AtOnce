package com.example.atonce.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.example.anees.ui.navigation.SetUpNavHost
import com.example.atonce.presentation.common.component.navigation.CustomBottomNavBar
import com.example.atonce.presentation.common.theme.AtOnceTheme
import com.example.atonce.presentation.common.theme.DarkWhiteColor
import com.example.atonce.presentation.common.theme.WhiteColor


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var bottomBarState: MutableState<Boolean>
    lateinit var snackbarHostState: SnackbarHostState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           // hideSystemUI()

            window.statusBarColor = if( isSystemInDarkTheme() ) DarkWhiteColor.toArgb() else  WhiteColor.toArgb()

            WindowCompat.setDecorFitsSystemWindows(window, false)

            WindowCompat.getInsetsController(window, window.decorView).apply {
                isAppearanceLightStatusBars = !isSystemInDarkTheme()
            }
            AtOnceTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                navController = rememberNavController()
                bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState, modifier = Modifier.padding(bottom = 50.dp)) },
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
                        snackbarState = snackbarHostState,
                        paddingValues = innerPadding
                    )
                }

            }
        }
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let {
                it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE        }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )    }
        window.statusBarColor = Color.Transparent.toArgb()
        window.navigationBarColor = Color.Transparent.toArgb()
    }
}











