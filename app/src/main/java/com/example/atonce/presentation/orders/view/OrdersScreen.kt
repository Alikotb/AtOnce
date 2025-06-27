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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.OrderEntity
import com.example.atonce.presentation.common.component.EmptyCart
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import com.example.atonce.presentation.orders.view.component.OrderCardShimmer
import com.example.atonce.presentation.orders.view.component.OrdersCard
import com.example.atonce.presentation.orders.view.component.OrdersChips
import com.example.atonce.presentation.orders.viewModel.OrdersViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun OrderScreen(modifier: PaddingValues, viewModel: OrdersViewModel = koinViewModel()) {

    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp

    val state by viewModel.orders.collectAsStateWithLifecycle()
    val columnState = rememberLazyListState()
    var statusChip by remember { mutableIntStateOf(0) }
    LaunchedEffect(Unit) {
        viewModel.getOrdersByStatus(status = 0)

    }

    LaunchedEffect(columnState) {
        snapshotFlow { columnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val totalItems = columnState.layoutInfo.totalItemsCount
                if (lastVisible == totalItems - 1) {
                    viewModel.getOrdersByStatus(
                        status = statusChip
                    )
                }
            }
    }
    val colors = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(
                top = modifier.calculateTopPadding(),
                bottom = modifier.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoIconCard(
            headerTxt = stringResource(R.string.orders_screen),
        )
        OrdersChips(
            onStateChange = {
                statusChip = it
                viewModel.resetPagination()
                viewModel.getOrdersByStatus(status = it)
            }
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 56.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = columnState,
            reverseLayout = false
        ) {
            when (state) {
                is Response.Error -> {
                    items(8) { NoInternet() }
                }

                Response.Loading -> {
                    items(4) { OrderCardShimmer() }
                }

                is Response.Success -> {
                    val list = (state as Response.Success<List<OrderEntity>>).data
                    if (list.isNotEmpty()) {
                        items(list) {
                            OrdersCard(it)
                        }
                    } else
                        item {
                            Spacer(Modifier.height((screenHeight*0.1).dp))
                            EmptyCart(resId = R.raw.no_data,messageInfo = stringResource(R.string.no_order_found)) }
                }
            }

        }

    }
}