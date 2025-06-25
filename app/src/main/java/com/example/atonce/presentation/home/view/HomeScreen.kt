package com.example.atonce.presentation.home.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.core.constants.AppConstants
import com.example.atonce.data.remote.Response
import com.example.atonce.presentation.common.component.EmptyCart
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.component.app_bar_cards.TowIconCard
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.home.view.component.AdPager
import com.example.atonce.presentation.home.view.component.ShimmerWarehouseCard
import com.example.atonce.presentation.home.view.component.TappableSearchBar
import com.example.atonce.presentation.home.view.component.WarehouseCard
import com.example.atonce.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onProfileClick: () -> Unit,onNavToStore: (Int) -> Unit, onNavToSearch: () -> Unit, modifier: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    val colors= MaterialTheme.colorScheme
    val ctx = LocalContext.current
    val ads = listOf(
        R.drawable.ads,
        R.drawable.ads1,
        R.drawable.ads2
    )

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.getWarehousesByArea(viewModel.getPharmacyId())
    }
    LaunchedEffect(listState){
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisible == totalItems - 1) {
                    viewModel.getWarehousesByArea(areaId = viewModel.getPharmacyId())
                }
            }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(top = modifier.calculateTopPadding()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            TowIconCard(
                onEndClick={
                    onProfileClick()
                },
                onStartClick = {
                    val u = ("tel:" + AppConstants.PHONE_NUMBER).toUri()
                    val i = Intent(Intent.ACTION_DIAL, u)
                    try {
                        ctx.startActivity(i)
                    } catch (s: SecurityException) {

                    }
                },
                headerTxt = stringResource(R.string.home_screen)
            )
        }

        item {
            AdPager(
                ads = ads,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(8.dp, RoundedCornerShape(12.dp))
            )
        }

        item {
            TappableSearchBar {
                onNavToSearch()
            }
        }

        item {
            Text(
                text = stringResource(R.string.warehouses_nearby),
                fontFamily = SemiBoldFont,
                fontSize = 20.sp,
                color = colors.primary,
                modifier = Modifier.padding(16.dp)
            )
        }

        when (val state = uiState.value) {
            is Response.Loading -> {
                items(5) { ShimmerWarehouseCard() }
            }
            is Response.Success -> {
                val warehouses = state.data

                if(warehouses.isEmpty()) {
                    item { EmptyCart(R.raw.no_data, "No warehouses found in this area") }
                }else {
                    items(warehouses) { warehouse ->
                        WarehouseCard(warehouse = warehouse) { onNavToStore(warehouse.id) }
                    }
                }
            }
            is Response.Error -> {
               item { NoInternet() }
            }
        }

        item {
            Spacer(modifier = Modifier.height(150.dp))
        }

    }
}


@Composable
fun CircularIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    tint: Color
) {
    val colors= MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(colors.surface)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
    }
}
