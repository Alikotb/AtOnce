package com.example.atonce.presentation.cart.veiw

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.data.remote.Response
import com.example.atonce.presentation.cart.veiw.components.AddToCartCard
import com.example.atonce.presentation.cart.veiw.components.OrderInfo
import com.example.atonce.presentation.cart.veiw.components.StoreTabs
import com.example.atonce.presentation.cart.viewModel.CartViewModel
import com.example.atonce.presentation.common.component.ProgressIndicator
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(modifier: PaddingValues , viewModel: CartViewModel = koinViewModel()) {
    val colors = MaterialTheme.colorScheme

    val items by viewModel.cartItems.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCartDetails()
    }

    var selectedStoreIndex by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(top = modifier.calculateTopPadding())
    ) {
      NoIconCard(
          headerTxt = stringResource(R.string.cart),
      )
        when (items) {
            is Response.Error -> {

            }
            Response.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    ProgressIndicator()
                }
            }
            is Response.Success -> {
                val stores = (items as Response.Success).data
                StoreTabs(
                    stores = stores,
                    selectedIndex = selectedStoreIndex,
                    onTabSelected = { selectedStoreIndex = it }
                )


                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 140.dp)
                ) {

                    items(stores[selectedStoreIndex].items) { item ->
                        AddToCartCard(
                            cartItem = item,
                            onIncrease = {},
                            onDecrease = {},
                            onDelete = { }
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(colors.surface)
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .padding(bottom = 56.dp)
                ) {
                    val subTotal = stores[selectedStoreIndex].totalPriceBeforeDiscount ?: 0.0
                    val total = stores[selectedStoreIndex].totalPriceAfterDiscount ?: 0.0
                    val discount = subTotal - total
                    val minimum = stores[selectedStoreIndex].minimumPrice ?: 0.0
                    OrderInfo(
                        subtotal = subTotal,
                        discount = discount,
                        total = total,
                        minimum = minimum,
                        onCheckout = { }
                    )
                }
            }
        }
    }
}



