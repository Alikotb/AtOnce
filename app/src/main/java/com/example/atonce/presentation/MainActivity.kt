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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowInsetsControllerCompat
import com.example.atonce.core.enums.LanguageEnum
import com.example.atonce.core.extensions.applyLanguage
import com.example.atonce.domain.usecase.GetLanguageUseCase
import com.example.atonce.presentation.common.navigation.SetUpNavHost
import com.example.atonce.presentation.common.component.navigation.CustomBottomNavBar
import com.example.atonce.presentation.common.theme.AtOnceTheme
import com.example.atonce.presentation.common.theme.DarkWhiteColor
import com.example.atonce.presentation.common.theme.WhiteColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.android.ext.android.inject
import org.koin.compose.koinInject
import java.util.Locale


class MainActivity : ComponentActivity() {
    lateinit var navController: NavHostController
    lateinit var bottomBarState: MutableState<Boolean>
    lateinit var snackbarHostState: SnackbarHostState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val lang: GetLanguageUseCase by inject()
        if (lang() == LanguageEnum.DEFAULT.code) {
            applyLanguage(this.resources.configuration.locales[0].language)
        } else {
            applyLanguage(lang())
        }
        setContent {
            HideSystemUI()
            AtOnceTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = false) {
                navController = rememberNavController()
                bottomBarState = rememberSaveable { (mutableStateOf(false)) }
                snackbarHostState = remember { SnackbarHostState() }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            modifier = Modifier.padding(bottom = 50.dp)
                        )
                    },
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

    @Composable
    private fun HideSystemUI() {
        val darkTheme = isSystemInDarkTheme()
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = if (darkTheme) DarkWhiteColor else WhiteColor,
                darkIcons = !darkTheme
            )
        }

        systemUiController.setNavigationBarColor(
            color = Color.Transparent,
            darkIcons = !darkTheme,
            navigationBarContrastEnforced = false
        )

        systemUiController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        systemUiController.isNavigationBarVisible = false
        systemUiController.isStatusBarVisible = true
    }
}











