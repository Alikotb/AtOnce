package com.example.atonce.presentation.component.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListNumbered
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.rounded.OtherHouses
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.atonce.presentation.navigation.ScreenRoute

sealed class BottomNavItem(
    val route: ScreenRoute,
    val icon: ImageVector
) {
    object Home : BottomNavItem(ScreenRoute.HomeScreen, Icons.Rounded.OtherHouses)
    object Search : BottomNavItem(ScreenRoute.SearchScreen, Icons.Default.Search)
    object Cart : BottomNavItem(ScreenRoute.CartScreen, Icons.Default.ShoppingBasket)
    object Order : BottomNavItem(ScreenRoute.OrderScreen, Icons.Default.FormatListNumbered)
}