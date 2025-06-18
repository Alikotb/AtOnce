package com.example.atonce.presentation.orders.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.comon.FontSizes.TITLE
import com.example.atonce.presentation.orders.view.component.OrdersCard
import com.example.atonce.presentation.orders.view.component.OrdersChips


@Preview(showBackground = true)
@Composable
fun OrderScreen(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, bottom = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (modifier = Modifier.fillMaxWidth()){
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.orders_screen),
                fontSize = TITLE.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding( top = 8.dp, bottom = 24.dp)
            )
            Spacer(Modifier.weight(1f))
        }
        OrdersChips()
        Spacer(Modifier.height(8.dp))
        LazyColumn (
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ){
            items(10){
                OrdersCard()

            }
        }

    }
}