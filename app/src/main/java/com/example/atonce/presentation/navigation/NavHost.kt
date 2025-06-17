package com.example.anees.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce.presentation.cart.CartScreen
import com.example.atonce.presentation.home.HomeScreen
import com.example.atonce.presentation.login.LoginScreen
import com.example.atonce.presentation.navigation.ScreenRoute
import com.example.atonce.presentation.orders.OrderScreen
import com.example.atonce.presentation.signup.SignUpScreen
import com.example.atonce.presentation.splash.SplashScreen
import com.example.atonce.presentation.store.StoreScreen


@Composable
fun SetUpNavHost(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.HomeScreen

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
                navToStore = {
                    navController.navigate(ScreenRoute.StoreScreen)
                },
                navToSearch = {
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
             LoginScreen()
        }
        composable<ScreenRoute.SignupScreen> {
            SignUpScreen()
        }
        composable<ScreenRoute.HomeScreen> {
            HomeScreen()
        }
        composable<ScreenRoute.StoreScreen> {
            StoreScreen()
        }
        composable<ScreenRoute.CartScreen> {
            CartScreen()
        }
        composable<ScreenRoute.OrderScreen> {
            OrderScreen()
        }

    }
}