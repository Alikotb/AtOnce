package com.example.atonce.presentation.store.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.comon.FontSizes.TITLE
import com.example.atonce.presentation.component.SearchComponent
import com.example.atonce.presentation.component.TapBarBtn
import com.example.atonce.presentation.theme.SemiBoldFont


@Preview(showBackground = true)
@Composable
fun StoreScreen(onBackClick: () -> Unit = {}){
    val colors = MaterialTheme.colorScheme
    var expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize().background(colors.onPrimary)
            .padding(top = 48.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (modifier = Modifier.fillMaxWidth()){
            TapBarBtn()
            Spacer(Modifier.weight(1f))
            Text(
                text = "Store Screen",
                fontSize = TITLE.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding( top = 8.dp, bottom = 24.dp),
                fontFamily = SemiBoldFont
            )
            Spacer(Modifier.weight(1f))
            Spacer(Modifier.weight(1f))

        }
        SearchComponent(expanded=expanded, onSearch = {
            searchText=it
        },
            onFilterClick = {
                filterSearch=it
            }

        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(horizontal = 12.dp),
            state = rememberLazyGridState(),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 64.dp
            ),
            reverseLayout = false,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
            items(10){
                MedicineCard()
            }
        }

    }

}
