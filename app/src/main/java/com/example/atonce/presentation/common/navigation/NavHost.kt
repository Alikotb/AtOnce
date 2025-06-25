package com.example.atonce.presentation.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.atonce.domain.internet.ConnectivityObserver
import com.example.atonce.presentation.cart.view.CartScreen
import com.example.atonce.presentation.home.view.HomeScreen
import com.example.atonce.presentation.login.view.LoginScreen
import com.example.atonce.presentation.no_internet.NoInternetScreen
import com.example.atonce.presentation.orders.view.OrderScreen
import com.example.atonce.presentation.profile.view.ProfileDetails
import com.example.atonce.presentation.profile.view.ProfileScreen
import com.example.atonce.presentation.search.view.SearchScreen
import com.example.atonce.presentation.signup.SignUpScreen
import com.example.atonce.presentation.splash.view.SplashScreen
import com.example.atonce.presentation.store.view.StoreScreen
import com.example.atonce.presentation.webview.WebViewScreen
import androidx.compose.runtime.getValue
import org.koin.compose.koinInject


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    snackbarState: SnackbarHostState,
    paddingValues: PaddingValues
) {
    val connectivityObserver: ConnectivityObserver = koinInject()
    val isOnline by connectivityObserver.isOnline.observeAsState(initial = true)
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.SplashScreen

    ) {

        composable<ScreenRoute.SplashScreen> {
            SplashScreen(
                onNavToHome = {
                    navController.navigate(ScreenRoute.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavToLogIn = {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<ScreenRoute.LoginScreen> {
            bottomBarState.value = false
            LoginScreen(
                modifier = paddingValues,
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
            if (isOnline) {
                SignUpScreen(
                    modifier = paddingValues,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    snackbarHostState = snackbarState,
                    onRegisterClick = {
                        navController.navigate(ScreenRoute.LoginScreen) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.HomeScreen> {
            bottomBarState.value = true
            if (isOnline) {
                HomeScreen(
                    modifier = paddingValues,
                    onProfileClick = {
                        navController.navigate(ScreenRoute.ProfileScreen)
                    },
                    onNavToStore = { warehouseId ->
                        navController.navigate(ScreenRoute.StoreScreen(warehouseId))
                    }
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.StoreScreen> {
            bottomBarState.value = false
            val warehouseId = it.toRoute<ScreenRoute.StoreScreen>().warehouseId
            if (isOnline) {
                StoreScreen(
                    warehouseId = warehouseId,
                    snackbarHostState = snackbarState,
                    modifier = paddingValues,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.CartScreen> {
            bottomBarState.value = true
            if (isOnline) {
                CartScreen(
                    modifier = paddingValues,
                    snackbarHostState = snackbarState
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.OrderScreen> {
            bottomBarState.value = true
            if (isOnline) {
                OrderScreen(modifier = paddingValues)
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.SearchScreen> {
            bottomBarState.value = true
            if (isOnline) {
                SearchScreen(
                    modifier = paddingValues,
                    snackbarHostState = snackbarState,
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.ProfileDetailsScreen> {
            bottomBarState.value = false
            ProfileDetails(modifier = paddingValues, onClick = {
                navController.popBackStack()
            })
        }
        composable<ScreenRoute.ProfileScreen> {
            bottomBarState.value = false
            ProfileScreen(
                modifier = paddingValues,
                onBackClick = {
                    navController.popBackStack()
                },
                onWebViewClick = { title, url ->
                    navController.navigate(ScreenRoute.WebViewScreen(title, url))
                },
                onDetailsClick = {
                    navController.navigate(ScreenRoute.ProfileDetailsScreen)
                },
                onLogoutClicK = {
                    navController.popBackStack()
                    navController.navigate(ScreenRoute.LoginScreen)
                }
            )
        }
        composable<ScreenRoute.WebViewScreen> {
            bottomBarState.value = false
            val title = it.toRoute<ScreenRoute.WebViewScreen>().title
            val url = it.toRoute<ScreenRoute.WebViewScreen>().url
            if (isOnline) {
                WebViewScreen(title = title, url = url, onBackClick = {
                    navController.popBackStack()
                })
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.NoInternetScreen> {
            bottomBarState.value = true
            NoInternetScreen(padding = paddingValues)
        }
    }
}