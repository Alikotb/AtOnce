package com.example.atonce.presentation.search.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.data.remote.Response
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.presentation.common.component.DotLoadingIndicator
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.component.SearchComponent
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import com.example.atonce.presentation.search.view.component.ModelSheet
import com.example.atonce.presentation.search.view.component.SearchCard
import com.example.atonce.presentation.search.view.component.ShimmerSearchCard
import com.example.atonce.presentation.search.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(modifier: PaddingValues, snackbarHostState: SnackbarHostState, viewModel: SearchViewModel = koinViewModel()) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }

    var selectedMedicine by remember { mutableStateOf<Medicine?>(null) }
    var areaId by remember { mutableStateOf(3) }

    val colors = MaterialTheme.colorScheme

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val currentLanguage by viewModel.currentLanguage
    val listState = rememberLazyListState()

    val isLoading by viewModel.isLoadingState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getMedicinesByArea(3, search = "")
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(listState, searchText) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisible ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (lastVisible == totalItems - 1) {
                    viewModel.getMedicinesByArea(areaId = 3, search = viewModel.searchQuery.value)
                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(top = modifier.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoIconCard(
            headerTxt = stringResource(R.string.search_screen)
        )

        SearchComponent(
            expanded = expanded, onSearch = {
                viewModel.onSearchChanged(it)
            },
            onFilterClick = {
                filterSearch = it
            }

        )

        LazyColumn(
            state = listState,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 32.dp),
            contentPadding = PaddingValues(
                bottom = 84.dp
            )
        ) {
            when (val state = uiState.value) {
                is Response.Loading -> {
                    items(2) {
                        ShimmerSearchCard()
                    }
                }

                is Response.Success -> {
                    val medicines = state.data
                    items(medicines) { medicine ->
                        SearchCard(
                            medicine = medicine,
                            btnEnabled = !isLoading,
                            language = currentLanguage,
                            onCartClick = {
                                viewModel.addToCart(
                                    medicine.warehouseIdOfMaxDiscount,
                                    medicine.medicineId
                                )
                            },
                            onSuppliersClick = {
                                selectedMedicine = it
                                showBottomSheet = true
                            })
                        Spacer(Modifier.height(8.dp))
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

    if (showBottomSheet) {
        ModelSheet(
            viewModel = viewModel,
            medicine = selectedMedicine,
            areaId = areaId
        ) {
            showBottomSheet = false
        }
    }
}




