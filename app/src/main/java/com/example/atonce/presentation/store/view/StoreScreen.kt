package com.example.atonce.presentation.store.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import com.example.atonce.presentation.component.MySearchBar
import com.example.atonce.presentation.component.TapBarBtn
import com.example.atonce.presentation.theme.SemiBoldFont


@Preview(showBackground = true)
@Composable
fun StoreScreen(){
    var expanded = remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var filterSearch by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
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
        }
        Row {
            MySearchBar(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                searchText = it
            }

            Spacer(modifier = Modifier.width(4.dp))

            Box {
                TapBarBtn(
                    icon = Icons.Filled.FilterList,
                    onIconClick = {
                        expanded.value = !expanded.value
                    }
                )

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Option 1") },
                        onClick = {
                            expanded.value = false
                            filterSearch = "Option 1"
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Option 2") },
                        onClick = {
                            expanded.value = false
                            filterSearch = "Option 1"

                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))

        }
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
