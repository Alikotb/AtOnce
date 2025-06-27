package com.example.atonce.presentation.orders.view.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CancelPresentation
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Loop
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atonce.R
import com.example.atonce.core.enums.OrderState
import com.example.atonce.presentation.common.theme.Til
import com.example.atonce.presentation.common.theme.WhiteColor


@Preview(showBackground = true)
@Composable
fun OrdersChips(onStateChange:(Int)-> Unit={}) {
    val list = OrderState.entries
    val defaultOption = OrderState.getName(OrderState.ORDERED.value)
    var selectedOption by remember { mutableStateOf(defaultOption) }

    Row(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        list.forEach {
            var isSelected = OrderState.getName(it.value)  == selectedOption
            val chipColor = if (isSelected) Til else WhiteColor
            FilterChip(
                selected = isSelected,
                onClick = {
                    selectedOption = OrderState.getName(it.value)
                    Log.d("ali", "OrdersChips: ${it.value}")
                    onStateChange(it.value)
                },
                label = {
                    Text( OrderState.getName(it.value))
                },
                leadingIcon = if (isSelected) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    {
                        Icon(
                            imageVector = it.icon,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = Til
                        )
                    }
                },
                colors= FilterChipDefaults.filterChipColors(
                    selectedContainerColor = chipColor,
                    selectedLabelColor = Color.White,

                ),
                border = BorderStroke(1.dp, Til)
            )
            Spacer(Modifier.padding(horizontal = 4.dp))
        }
    }
}

