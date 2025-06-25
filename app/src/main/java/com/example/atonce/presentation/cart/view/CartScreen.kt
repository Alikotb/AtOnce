package com.example.atonce.presentation.cart.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.data.remote.Response
import com.example.atonce.data.remote.dto.cart.UpdateCartRequest
import com.example.atonce.presentation.cart.view.components.*
import com.example.atonce.presentation.cart.viewModel.CartViewModel
import com.example.atonce.presentation.common.component.DeleteConfirmationDialog
import com.example.atonce.presentation.common.component.EmptyCart
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import kotlinx.coroutines.flow.collect
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(
    modifier: PaddingValues,
    viewModel: CartViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState
) {
    val colors = MaterialTheme.colorScheme

    val items by viewModel.cartItems.collectAsStateWithLifecycle()
    val isClicked by viewModel.isUpdated.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getCartDetails()
    }

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    var selectedStoreIndex by remember { mutableStateOf(0) }
    var isDialogOpen by remember { mutableStateOf(false) }
    var selectedMedicineId by remember { mutableStateOf<Int?>(null) }

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
                // Handle error if needed
            }

            Response.Loading -> {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(3) {
                        ShimmerCartCard()
                    }
                }
            }

            is Response.Success -> {
                val stores = (items as Response.Success).data

                if (stores.isEmpty()){
                    EmptyCart(messageInfo = stringResource(R.string.no_orders_yet_add_some_orders_to_your_cart))
                }
                else{
                    StoreTabs(
                        stores = stores,
                        selectedIndex = selectedStoreIndex,
                        onTabSelected = { selectedStoreIndex = it }
                    )

                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 140.dp)
                    ) {
                        items(
                            items = stores[selectedStoreIndex].items,
                            key = { it.medicineId }
                        ) { item ->
                            AddToCartCard(
                                enapled = isClicked,
                                cartItem = item,
                                onIncrease = {
                                    viewModel.updateCartAndRefresh(
                                        UpdateCartRequest(
                                            newQuantity = item.quantity + 1,
                                            medicineId = item.medicineId,
                                            warehouseId = stores[selectedStoreIndex].warehouseId
                                        )
                                    )
                                },
                                onDecrease = {
                                    viewModel.updateCartAndRefresh(
                                        UpdateCartRequest(
                                            newQuantity = item.quantity - 1,
                                            medicineId = item.medicineId,
                                            warehouseId = stores[selectedStoreIndex].warehouseId
                                        )
                                    )
                                },
                                onDelete = {
                                    selectedMedicineId = item.medicineId
                                    isDialogOpen = true

                                    viewModel.deleteFromCart(
                                        wareHouseId = stores[selectedStoreIndex].warehouseId,
                                        medicineId = item.medicineId
                                    )
                                },
                                onQuantityChange = { newQuantity ->
                                    viewModel.updateCartAndRefresh(
                                        UpdateCartRequest(
                                            newQuantity = newQuantity,
                                            medicineId = item.medicineId,
                                            warehouseId = stores[selectedStoreIndex].warehouseId,
                                        )
                                    )
                                },
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(4.dp, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
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
                            onCheckout = { /* Handle checkout */ }
                        )
                    }

                    if (isDialogOpen && selectedMedicineId != null) {
                        DeleteConfirmationDialog(
                            onDismiss = {
                                isDialogOpen = false
                                selectedMedicineId = null
                            },
                            onConfirm = {
                                viewModel.deleteFromCart(
                                    wareHouseId = stores[selectedStoreIndex].warehouseId,
                                    medicineId = selectedMedicineId ?: return@DeleteConfirmationDialog
                                )
                                isDialogOpen = false
                                selectedMedicineId = null
                            }
                        )
                    }
                }
            }
        }
    }
}
