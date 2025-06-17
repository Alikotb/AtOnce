package com.example.atonce.presentation.component.navigation

import com.example.atonce.R
import com.example.atonce.presentation.navigation.ScreenRoute

sealed class BottomNavItem(
    val route: ScreenRoute,
    val iconRes: Int
) {
    object Home : BottomNavItem(ScreenRoute.HomeScreen, R.drawable.home)
    object Search : BottomNavItem(ScreenRoute.SearchScreen, R.drawable.search)
    object Cart : BottomNavItem(ScreenRoute.CartScreen, R.drawable.cart)
    object Order : BottomNavItem(ScreenRoute.OrderScreen, R.drawable.order)
}