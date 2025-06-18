package com.example.atonce.presentation.component.navigation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.atonce.presentation.navigation.ScreenRoute
import com.example.atonce.presentation.theme.PrimaryColor
import com.example.atonce.presentation.theme.WhiteColor


@Composable
fun CustomBottomNavBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Cart,
        BottomNavItem.Order
    )

    Surface(
        modifier = Modifier
            .height(80.dp)
            .padding(start = 22.dp, end = 22.dp, top = 16.dp),
        shape = RoundedCornerShape(18.dp),
        color = Color(0xFF25252D),
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val routeName = item.route::class.simpleName.toString()
                val isSelected = currentDestination?.contains(routeName) == true

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.height(70.dp)
                ) {
                    IconButton(onClick = {
                        navController.navigate(item.route) {
                            popUpTo(ScreenRoute.HomeScreen) { inclusive = false }
                            launchSingleTop = true
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = item.route.toString(),
                            tint = if (isSelected) PrimaryColor else WhiteColor,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    this@Row.AnimatedVisibility(
                        visible = isSelected,
                        enter = slideInVertically { it } + fadeIn(),
                        exit = slideOutVertically { it } + fadeOut(),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .width(26.dp)
                                .height(3.dp)
                                .background(PrimaryColor, RoundedCornerShape(50))
                        )
                    }
                }
            }
        }
    }
}
