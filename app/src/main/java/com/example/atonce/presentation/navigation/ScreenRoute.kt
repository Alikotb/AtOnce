package com.example.atonce.presentation.navigation

import kotlinx.serialization.Serializable


sealed class ScreenRoute {
    @Serializable
    object SplashScreen : ScreenRoute()
    @Serializable
    object HomeScreen : ScreenRoute()

    @Serializable
    object LoginScreen : ScreenRoute()
    @Serializable
    object SignupScreen : ScreenRoute()

    @Serializable
    object StoreScreen : ScreenRoute()
    @Serializable
    object SearchScreen : ScreenRoute()

    @Serializable
    object CartScreen : ScreenRoute()
    @Serializable
    object OrderScreen : ScreenRoute()

    @Serializable
    object ProfileScreen : ScreenRoute()

}