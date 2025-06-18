package com.example.atonce.presentation.cart.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.cart.Store
import com.example.atonce.presentation.theme.MediumFont

@Composable
fun StoreTabs(
    stores: List<Store>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val colors = MaterialTheme.colorScheme

    val tabWidth = (LocalConfiguration.current.screenWidthDp.dp - 32.dp) / 3

    ScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = colors.onPrimary,
        edgePadding = 16.dp,
        divider = {},
        modifier = Modifier.fillMaxWidth()
    ) {
        stores.forEachIndexed { index, store ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        store.name,
                        fontFamily = MediumFont,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.width(tabWidth)
            )
        }
    }
}