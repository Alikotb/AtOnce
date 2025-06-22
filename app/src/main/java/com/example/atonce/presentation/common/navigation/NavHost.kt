package com.example.anees.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.atonce.presentation.cart.CartScreen
import com.example.atonce.presentation.home.view.HomeScreen
import com.example.atonce.presentation.login.view.LoginScreen
import com.example.atonce.presentation.orders.view.OrderScreen
import com.example.atonce.presentation.search.view.SearchScreen
import com.example.atonce.presentation.common.navigation.ScreenRoute
import com.example.atonce.presentation.profile.view.ProfileDetails
import com.example.atonce.presentation.profile.view.ProfileScreen
import com.example.atonce.presentation.signup.SignUpScreen
import com.example.atonce.presentation.splash.SplashScreen
import com.example.atonce.presentation.store.view.StoreScreen
import com.example.atonce.presentation.webview.WebViewScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    snackbarState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.LoginScreen

    ) {
        composable<ScreenRoute.SplashScreen> {
            SplashScreen(
                navToHome = {
                navController.navigate(ScreenRoute.HomeScreen)
            },
                navToLogin = {
                    navController.navigate(ScreenRoute.LoginScreen)
                },
                navToSignUp = {
                    navController.navigate(ScreenRoute.SignupScreen)
                },
                navToSearch = {
                    navController.navigate(ScreenRoute.SearchScreen)
                },
                navToOrders = {
                    navController.navigate(ScreenRoute.OrderScreen)
                },
                navToCart = {
                    navController.navigate(ScreenRoute.CartScreen)
                },
                navToProfile = {

                }

            )
        }
        composable<ScreenRoute.LoginScreen> {
            bottomBarState.value = false
             LoginScreen(
                 modifier =paddingValues,
                 snackbarHostState = snackbarState,
                 onRegisterClick = {
                     navController.navigate(ScreenRoute.SignupScreen)
                 },
                 onLoginClick = {
                     navController.navigate(ScreenRoute.HomeScreen) {
                         popUpTo(0) { inclusive = true }
                         launchSingleTop = true
                     }
                 }
             )
        }
        composable<ScreenRoute.SignupScreen> {
            bottomBarState.value = false
            SignUpScreen(
                modifier =paddingValues,
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterClick = {
                    navController.navigate(ScreenRoute.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<ScreenRoute.HomeScreen> {
            bottomBarState.value = true
            HomeScreen(
                modifier =paddingValues,
                onProfileClick = {
                    navController.navigate(ScreenRoute.ProfileScreen)
                },
                onNavToStore = { warehouseId ->
                    navController.navigate(ScreenRoute.StoreScreen(warehouseId))
                }
            )
        }
        composable<ScreenRoute.StoreScreen> {
            bottomBarState.value = false
            val warehouseId = it.toRoute<ScreenRoute.StoreScreen>().warehouseId
            StoreScreen(
                warehouseId = warehouseId,
                modifier =paddingValues,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable<ScreenRoute.CartScreen> {
            bottomBarState.value = true
            CartScreen(
                modifier =paddingValues,
                onProfileClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenRoute.ProfileScreen)
                },
                onCallClick = {
                    navController.popBackStack()
                    navController.navigate(ScreenRoute.LoginScreen)
                }
            )
        }
        composable<ScreenRoute.OrderScreen> {
            bottomBarState.value = true
            OrderScreen(modifier =paddingValues)
        }
        composable<ScreenRoute.SearchScreen> {
            bottomBarState.value = true
            SearchScreen( modifier =paddingValues,)
        }
        composable<ScreenRoute.ProfileDetailsScreen> {
            bottomBarState.value = false
            ProfileDetails( modifier =paddingValues,onClick={
                navController.popBackStack()
            })
        }
        composable<ScreenRoute.ProfileScreen> {
            bottomBarState.value = false
            ProfileScreen(
                modifier =paddingValues,
                onBackClick = {
                    navController.popBackStack()
                },
                onWebViewClick = { title, url ->
                    navController.navigate(ScreenRoute.WebViewScreen(title, url))
                },
                onDetailsClick = {
                    navController.navigate(ScreenRoute.ProfileDetailsScreen)
                }
            )
        }
        composable<ScreenRoute.WebViewScreen> {
            bottomBarState.value = false
            val title =it.toRoute<ScreenRoute.WebViewScreen>().title
            val url = it.toRoute<ScreenRoute.WebViewScreen>().url
            WebViewScreen(title = title, url = url, onBackClick = {
                navController.popBackStack()
            })
        }
    }
}