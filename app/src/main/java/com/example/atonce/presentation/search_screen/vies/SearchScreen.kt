package com.example.atonce.presentation.search_screen.vies

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.atonce.presentation.search_screen.vies.component.ModelSheet
import com.example.atonce.presentation.search_screen.vies.component.SearchCard
import com.example.atonce.presentation.theme.SemiBoldFont

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun SearchScreen(){
    var showBottomSheet by remember { mutableStateOf(false) }
    val expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }
    Log.d("ali", "SearchScreen: search: $searchText // filter : $filterSearch")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (modifier = Modifier.fillMaxWidth().padding(start = 8.dp)){
            TapBarBtn()
            Spacer(Modifier.weight(0.75f))
            Text(
                text = "Search Screen",
                fontSize = TITLE.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding( top = 16.dp, bottom = 16.dp),
                fontFamily = SemiBoldFont
            )
            Spacer(Modifier.weight(1f))
        }
        SearchComponent(expanded=expanded, onSearch = {
            searchText=it
        },
            onFilterClick = {
                filterSearch=it
            }

        )

        LazyColumn(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 32.dp)) {
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




