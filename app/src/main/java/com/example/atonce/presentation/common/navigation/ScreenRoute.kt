package com.example.atonce.presentation.common.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute(val route: String) {
    @Serializable
    object SplashScreen : ScreenRoute("splash")

    @Serializable
    object NoInternetScreen : ScreenRoute("noInternetScreen")
    @Serializable
    object HomeScreen : ScreenRoute("home")
    @Serializable
    object LoginScreen : ScreenRoute("login")
    @Serializable
    object SignupScreen : ScreenRoute("signup")
    @Serializable
    data class StoreScreen(val warehouseId: Int,val warehouseName: String) : ScreenRoute("store")
    @Serializable
    object SearchScreen : ScreenRoute("search")
    @Serializable
    object CartScreen : ScreenRoute("cart")
    @Serializable
    object OrderScreen : ScreenRoute("order")
    @Serializable
    object ProfileScreen : ScreenRoute("profile")
    @Serializable
    object ProfileDetailsScreen : ScreenRoute("profileDetails")
    @Serializable
    data class WebViewScreen(val title : String, val url : String) : ScreenRoute("webview")
}
