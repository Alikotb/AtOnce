package com.example.atonce.presentation.common.component


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.theme.RegularFont

@Composable
fun MySearchBar(modifier: Modifier, onValueChange : (String)-> Unit={}){
    var searchText by remember { mutableStateOf("") }
    val colors = MaterialTheme.colorScheme
    TextField(
        modifier = modifier,
        value = searchText,
        onValueChange = {
            onValueChange(it)
            searchText=it
        },
        shape = MaterialTheme.shapes.medium,
        placeholder = {Text(stringResource(R.string.search_for_medecin), fontFamily = RegularFont, fontSize = 12.sp)},
        leadingIcon ={ Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = ""
        )},
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colors.surface,
            unfocusedContainerColor = colors.surface,
            cursorColor = colors.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = colors.onBackground,
            unfocusedTextColor = colors.onBackground

        )

    )
}

@Composable
fun SearchComponent(expanded: MutableState<Boolean>, onFilterClick : (String) -> Unit ={}, onSearch : (String) -> Unit ={}, listOfFiltration: List<String> = listOf("Option 1", "Option 2")){
    Row {
        MySearchBar(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
           onSearch(it)
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
                listOfFiltration.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            expanded.value = false
                            onFilterClick(option)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(4.dp))
    }
}
