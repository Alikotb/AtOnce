package com.example.atonce.presentation.store.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.core.enums.FilterOptions
import com.example.atonce.data.remote.Response
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.component.SearchComponent
import com.example.atonce.presentation.common.component.app_bar_cards.OneIconCard
import com.example.atonce.presentation.store.model.WarehouseMedicines
import com.example.atonce.presentation.store.view.component.MedicineCard
import com.example.atonce.presentation.store.view.component.MedicineCardShimmer
import com.example.atonce.presentation.store.viewmodel.WarehouseViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.compose.koinViewModel


@FlowPreview
@Composable
fun StoreScreen(
    warehouseId: Int,
    snackbarHostState: SnackbarHostState,
    modifier: PaddingValues,
    onBackClick: () -> Unit = {},
    viewModel: WarehouseViewModel = koinViewModel(),
    warehouseName: String
) {
    val colors = MaterialTheme.colorScheme
    var expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }


    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val loadingItemId by viewModel.loadingItemId.collectAsStateWithLifecycle()
    val gState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }


    LaunchedEffect(gState) {
        snapshotFlow { gState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val totalItems = gState.layoutInfo.totalItemsCount
                if (lastVisible == totalItems - 1) {
                    viewModel.getAllMedicinesByStoreId(
                        warehouseId = warehouseId,
                        search = viewModel.searchQuery.value,
                        type = viewModel.filterType.value
                    )
                }
            }
    }



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
        OneIconCard(
            headerTxt = warehouseName,
            onClick = { onBackClick() }
        )
        SearchComponent(
            expanded = expanded,
            onSearch = {
                searchText = it
                viewModel.onSearchChanged(it)
            },
            onFilterClick = {
                filterSearch = FilterOptions.geValue(it)
                viewModel.onFilterChanged(filterSearch)
            },
            listOfFiltration = FilterOptions.getAllLocalizedNames(),
            selectedOption = remember { mutableStateOf(FilterOptions.getName(filterSearch)) }

        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 12.dp),
            state = gState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 64.dp
            ),
            reverseLayout = false,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

            ) {
            when (uiState) {
                is Response.Loading -> {
                    items(8) { MedicineCardShimmer() }
                }

                is Response.Success -> {
                    val list = (uiState as Response.Success<List<WarehouseMedicines>>).data
                    items(list) {
                        MedicineCard(
                            obj = it,
                            onClick = {
                                viewModel.addToCart(warehouseId, it.medicineId)
                            },
                            enabled = loadingItemId != it.medicineId
                        )
                    }
                }

                is Response.Error -> {
                    item {
                        NoInternet()
                    }
                }
            }

        }

    }

}
