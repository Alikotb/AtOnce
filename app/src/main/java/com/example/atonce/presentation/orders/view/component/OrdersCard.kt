package com.example.atonce.presentation.orders.view.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.comon.FontSizes.ORDER_DATE_AND_TIME
import com.example.atonce.presentation.comon.FontSizes.ORDER_PHARMACY_NAME
import com.example.atonce.presentation.comon.FontSizes.SUB_ORDER_TITLE
import kotlin.collections.listOf


@Preview(showBackground = true)
@Composable
fun OrdersCard(){
    var expanded by remember { mutableStateOf(false) }
    val medicineList = listOf(
        Triple("Panadol Extra 600mg", "3 items", "150 EGP"),
        Triple("Brufen 400mg", "2 items", "90 EGP"),
        Triple("Vitamin D3", "1 item", "60 EGP"),
        Triple("Amoxicillin 500mg", "4 items", "200 EGP")
    )
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                expanded =! expanded
            })
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffFBFBFB)
        ),
        elevation = CardDefaults.cardElevation(  defaultElevation = 4.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    )
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (expanded) {
                Row {
                    Text(
                        text = "#747227",
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                    )
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Warehouse,
                            contentDescription = "",
                            tint = Color(0xFF1A998E),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        Text(
                            text = "UM Pharma",
                            fontSize = ORDER_PHARMACY_NAME.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        if (!expanded) {
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = "id: #747227",
                                fontSize = ORDER_PHARMACY_NAME.sp,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = "",
                            tint = Color(0xFF1A998E),
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        Text(
                            text = "25/05/2024 - 18:00 PM",
                            fontSize = ORDER_PHARMACY_NAME.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        Spacer(Modifier.weight(1f))
                    }

                }
                IconButton(onClick = {
                    expanded = !expanded
                }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "nill",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
            if (expanded) {
                Row {
                    Text(
                        text = "Zefta , Gharbia",
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(start = 45.dp)
                    )
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(8.dp))
            if (expanded) {
                medicineList.forEach {
                    OrderSubCard(it)
                }
                HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
                Spacer(Modifier.height(8.dp))
                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "Total : 600 EGP",
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(end = 16.dp),
                        color = Color.Red
                    )

                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun OrderSubCard(item : Triple<String, String, String> = Triple("Panadol Extra 600mg","3 items","150 EGP")){
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp,end=4.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(4f)
        ){
            Icon(
                imageVector = Icons.Filled.Coronavirus,
                contentDescription = ""

            )
            Text(
                text = item.first,
                fontSize = SUB_ORDER_TITLE.sp,
                modifier = Modifier.padding(start = 8.dp),

            )
        }
        Spacer(Modifier.weight(0.3f))
        Text(
            text = item.second,
            fontSize = SUB_ORDER_TITLE.sp,
            modifier = Modifier.weight(1f)

        )
        Spacer(Modifier.weight(0.3f))
        Text(
            text = item.third,
            fontSize = SUB_ORDER_TITLE.sp,
            color = Color(0xFF1A998E),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }

}