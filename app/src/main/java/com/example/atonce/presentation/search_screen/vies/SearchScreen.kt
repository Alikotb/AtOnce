package com.example.atonce.presentation.search_screen.vies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.atonce.R
import com.example.atonce.presentation.common.component.SearchComponent
import com.example.atonce.presentation.common.component.app_bar_cards.NoIconCard
import com.example.atonce.presentation.search_screen.vies.component.ModelSheet
import com.example.atonce.presentation.search_screen.vies.component.SearchCard

@ExperimentalMaterial3Api
@Composable
fun SearchScreen(modifier: PaddingValues) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }
    val colors = MaterialTheme.colorScheme
    Column(
        modifier = Modifier
            .fillMaxSize().background(colors.onPrimary)
            .padding(top = modifier.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        NoIconCard(
            headerTxt = stringResource(R.string.search_screen)
        )

        SearchComponent(expanded=expanded, onSearch = {
            searchText=it
        },
            onFilterClick = {
                filterSearch=it
            }

        )

        LazyColumn(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 32.dp)
        ,
            contentPadding = PaddingValues(
                bottom = 84.dp
            )
        ) {
            items(10) {
                SearchCard(onSuppliersClick = {
                    showBottomSheet = true
                })
                Spacer(Modifier.height(8.dp))
            }
        }

    }



    if (showBottomSheet) {
        ModelSheet(onClose = {
            showBottomSheet = false
        })
    }
}




