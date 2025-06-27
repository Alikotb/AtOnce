package com.example.atonce.presentation.orders.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.atonce.R
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import com.example.atonce.presentation.orders.view.component.OrdersCard
import com.example.atonce.presentation.orders.view.component.OrdersChips


@Composable
fun OrderScreen(modifier: PaddingValues) {

    val colors= MaterialTheme.colorScheme
    Column (
        modifier = Modifier
            .fillMaxSize().background(colors.onPrimary)
            .padding(top = modifier.calculateTopPadding(), bottom = modifier.calculateBottomPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       NoIconCard(
           headerTxt = stringResource(R.string.orders_screen),
       )
        OrdersChips()
        Spacer(Modifier.height(8.dp))
        LazyColumn (
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 56.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            items(10){
                OrdersCard()

            }
        }

    }
}