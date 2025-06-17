package com.example.atonce.presentation.component


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.atonce.presentation.theme.RegularFont

@Composable
fun MySearchBar(modifier: Modifier, onValueChange : (String)-> Unit={}){
    var searchText by remember { mutableStateOf("") }

    TextField(
        modifier = modifier,
        value = searchText,
        onValueChange = {
            onValueChange(it)
            searchText=it
        },
        shape = MaterialTheme.shapes.extraLarge,
        placeholder = {Text("Search for medecin", fontFamily = RegularFont)},
        leadingIcon ={ Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = ""
        )},
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

        )

    )
}