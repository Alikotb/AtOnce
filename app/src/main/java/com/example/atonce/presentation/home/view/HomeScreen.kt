package com.example.atonce.presentation.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.presentation.common.component.MySearchBar
import com.example.atonce.presentation.common.component.app_bar_cards.TowIconCard
import com.example.atonce.presentation.home.view.component.AdPager
import com.example.atonce.presentation.home.view.component.WarehouseCard
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.home.model.WarehouseUiModel
import com.example.atonce.presentation.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(onProfileClick: () -> Unit,onNavToStore: () -> Unit, modifier: PaddingValues, viewModel: HomeViewModel = koinViewModel()) {
    val colors= MaterialTheme.colorScheme

    val ads = listOf(
        R.drawable.ads,
        R.drawable.ads1,
        R.drawable.ads2
    )

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(listState){

    }

    LazyColumn(
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
            MySearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .shadow(4.dp, RoundedCornerShape(12.dp))
            )

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

        items(warehouses) { warehouse ->
            WarehouseCard(warehouse = warehouse) { onNavToStore() }
        }

        item {
            Spacer(modifier = Modifier.height(36.dp))
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
