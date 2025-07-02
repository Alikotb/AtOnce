package com.example.atonce.presentation.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.atonce.presentation.cart.view.CartScreen
import com.example.atonce.presentation.forgotpassword.view.ForgotPasswordScreen
import com.example.atonce.presentation.forgotpassword.view.component.EmailScreen
import com.example.atonce.presentation.forgotpassword.view.component.NewPasswordScreen
import com.example.atonce.presentation.forgotpassword.view.component.OtpScreen
import com.example.atonce.presentation.forgotpassword.view.component.SuccessResetScreen
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
import kotlinx.coroutines.FlowPreview


@FlowPreview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController,
    bottomBarState: MutableState<Boolean>,
    snackbarState: SnackbarHostState,
    paddingValues: PaddingValues,
    isOnline: Boolean
) {
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
                snackBarHostState = snackbarState,
                onRegisterClick = {
                    navController.navigate(ScreenRoute.SignupScreen)
                },
                onLoginClick = {
                    navController.navigate(ScreenRoute.HomeScreen) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(ScreenRoute.ForgotPasswordScreen)
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
                    snackBarHostState = snackbarState,
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
                    onNavToSearch = {
                        navController.navigate(ScreenRoute.SearchScreen)
                    },
                    onNavToStore = { warehouseId,warehouseName ->
                        navController.navigate(ScreenRoute.StoreScreen(warehouseId,warehouseName))
                    }
                )
            } else {
                NoInternetScreen()
            }
        }
        composable<ScreenRoute.StoreScreen> {
            bottomBarState.value = false
            val warehouseId = it.toRoute<ScreenRoute.StoreScreen>().warehouseId
            val warehouseName = it.toRoute<ScreenRoute.StoreScreen>().warehouseName

            if (isOnline) {
                StoreScreen(
                    warehouseId = warehouseId,
                    snackBarHostState = snackbarState,
                    modifier = paddingValues,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    warehouseName = warehouseName
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
                    snackBarHostState = snackbarState
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
                    snackBarHostState = snackbarState,
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
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        composable<ScreenRoute.WebViewScreen> {
            bottomBarState.value = false
            val title = it.toRoute<ScreenRoute.WebViewScreen>().title
            val url = it.toRoute<ScreenRoute.WebViewScreen>().url
            if (isOnline) {
                WebViewScreen(title = title, url = url, padding = paddingValues,onBackClick = {
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
        composable<ScreenRoute.ForgotPasswordScreen> {
            bottomBarState.value = false
            ForgotPasswordScreen(
                snackbarHostState = snackbarState,
                onBackClick = {
                    navController.popBackStack()
                },
                onContinueClick = {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(ScreenRoute.ForgotPasswordScreen) { inclusive = true }
                    }
                }
            )
        }
        composable<ScreenRoute.EmailScreen> {
            bottomBarState.value = false
                EmailScreen(
                    onBackClick = { navController.popBackStack() },
                )

        }
        composable<ScreenRoute.OtpScreen> {
            bottomBarState.value = false
            val email = it.toRoute<ScreenRoute.OtpScreen>().email
            OtpScreen(
                email = email,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
        composable<ScreenRoute.ResetPasswordScreen> {
            bottomBarState.value = false
            NewPasswordScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
        composable<ScreenRoute.ResetSuccessScreen> {
            bottomBarState.value = false
            SuccessResetScreen(
                onContinueClick = {
                    navController.navigate(ScreenRoute.LoginScreen) {
                        popUpTo(ScreenRoute.ForgotPasswordScreen) { inclusive = true }
                    }
                }
            )

        }
    }
}