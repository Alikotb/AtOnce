package com.example.atonce.presentation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.atonce.R
import com.example.atonce.presentation.cart.components.AddToCartCard
import com.example.atonce.presentation.cart.components.CartHeader
import com.example.atonce.presentation.cart.components.OrderInfo
import com.example.atonce.presentation.cart.components.StoreTabs



@Composable
fun CartScreen(onProfileClick: () -> Unit, onCallClick: () -> Unit) {
    val stores = remember {
        listOf(
            Store(
                id = "store1",
                name = "store1",
                items = listOf(
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 28, 200, 1),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 10, 150, 2),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 5, 80, 1),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 5, 80, 1)
                )
            ),
            Store(
                id = "store2",
                name = "store2",
                items = listOf(
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 15, 120, 3),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 0, 90, 2)
                )
            ),
            Store(
                id = "store3",
                name = "store3",
                items = listOf(
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 20, 180, 1),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 10, 220, 1)
                )
            ),
            Store(
                id = "store4",
                name = "store4",
                items = listOf(
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 0, 50, 5),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 5, 75, 2)
                )
            ),
            Store(
                id = "store5",
                name = "store5",
                items = listOf(
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 0, 50, 5),
                    CartItem(R.drawable.medicin_card_img, "Panadol Extra 600mg", 5, 75, 2)
                )
            )
        )
    }

    var selectedStoreIndex by remember { mutableStateOf(0) }
    val currentStore = stores[selectedStoreIndex]
    val cartItems = currentStore.items

    val (subtotal, discount, total) = remember(cartItems) {
        var subTotal = 0.0
        var totalDiscount = 0.0

        cartItems.forEach { item ->
            val itemTotal = item.unitCost * item.quantity
            subTotal += itemTotal
            totalDiscount += (itemTotal * item.discount / 100.0)
        }

        Triple(subTotal, totalDiscount, subTotal - totalDiscount)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 16.dp)
    ) {
        CartHeader(
            onCallClick = { onCallClick() },
            onProfileClick = { onProfileClick() }
        )

        StoreTabs(
            stores = stores,
            selectedIndex = selectedStoreIndex,
            onTabSelected = { selectedStoreIndex = it }
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 140.dp)
        ) {
            items(cartItems) { item ->
                AddToCartCard(
                    imageResId = item.imageResId,
                    medicationName = item.name,
                    discountPercent = item.discount,
                    costPerItem = item.unitCost,
                    quantity = item.quantity,
                    onIncrease = { item.quantity++ },
                    onDecrease = { if (item.quantity > 1) item.quantity-- },
                    onDelete = {  }
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .shadow(elevation = 4.dp, shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .padding(bottom = 56.dp)
        ) {
            OrderInfo(
                subtotal = subtotal,
                discount = discount,
                total = total,
                onCheckout = {  }
            )
        }
    }
}

data class Store(
    val id: String,
    val name: String,
    val items: List<CartItem>
)

data class CartItem(
    val imageResId: Int,
    val name: String,
    val discount: Int,
    val unitCost: Int,
    var quantity: Int
)