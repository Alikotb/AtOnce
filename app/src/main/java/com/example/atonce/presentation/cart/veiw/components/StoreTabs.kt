package com.example.atonce.presentation.cart.veiw.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.domain.entity.CartWarehouseEntity
import com.example.atonce.presentation.common.theme.MediumFont

@Composable
fun StoreTabs(
    stores: List<CartWarehouseEntity>,
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
                        store.warehouseName ?: "store ${store.warehouseId}",
                        fontFamily = MediumFont,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier.width(tabWidth)
            )
        }
    }
}