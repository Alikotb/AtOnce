package com.example.anees.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.atonce.presentation.cart.CartScreen
import com.example.atonce.presentation.home.HomeScreen
import com.example.atonce.presentation.login.view.LoginScreen
import com.example.atonce.presentation.orders.view.OrderScreen
import com.example.atonce.presentation.search_screen.vies.SearchScreen
import com.example.atonce.presentation.navigation.ScreenRoute
import com.example.atonce.presentation.signup.SignUpScreen
import com.example.atonce.presentation.splash.SplashScreen
import com.example.atonce.presentation.store.view.StoreScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpNavHost(
    navController: NavHostController,
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
                navToStore = {
                    navController.navigate(ScreenRoute.StoreScreen)
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
        composable<ScreenRoute.SearchScreen> {
            SearchScreen()
        }

    }
}