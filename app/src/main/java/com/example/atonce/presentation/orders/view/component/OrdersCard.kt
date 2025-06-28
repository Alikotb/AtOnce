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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.core.extensions.convertNumbersToArabic
import com.example.atonce.domain.entity.OrderEntity
import com.example.atonce.presentation.common.FontSizes.ORDER_DATE_AND_TIME
import com.example.atonce.presentation.common.FontSizes.ORDER_PHARMACY_NAME


@Composable
fun OrdersCard(order: OrderEntity) {
    var expanded by remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                expanded = !expanded
            })
        ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(  defaultElevation = 4.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
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
                        text = stringResource(R.string.id)+order.orderID.convertNumbersToArabic(),
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                        color= colors.onBackground
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
                            tint = colors.primary,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        Text(
                            text = order.wareHouseName,
                            fontSize = ORDER_PHARMACY_NAME.sp,
                            modifier = Modifier.padding(start = 8.dp),
                            color= colors.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                        if (!expanded) {
                            Spacer(Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.id)+order.orderID.convertNumbersToArabic(),
                                fontSize = ORDER_PHARMACY_NAME.sp,
                                modifier = Modifier.padding(start = 16.dp),
                                color= colors.onBackground


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
                            tint = colors.primary,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        Text(
                            text = order.orderDate.convertNumbersToArabic()+ stringResource(R.string.pm),
                            fontSize = ORDER_PHARMACY_NAME.sp,
                            modifier = Modifier.padding(start = 8.dp),
                            color= colors.onBackground,
                            fontWeight = FontWeight.Bold

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
                        text = stringResource(R.string.quantity) +order.address.convertNumbersToArabic(),
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(start = 45.dp),
                        color= colors.onBackground

                    )
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(Modifier.height(8.dp))
            if (expanded) {
                order.orderList.forEach {
                    OrderSubCard(it)
                }
                HorizontalDivider(modifier = Modifier.padding(top = 4.dp))
                Spacer(Modifier.height(8.dp))
                Row {
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(R.string.total) +"${order.total}".convertNumbersToArabic()+stringResource(R.string.egp),
                        fontSize = ORDER_DATE_AND_TIME.sp,
                        modifier = Modifier.padding(end = 16.dp),
                        color = Color.Red,

                    )

                }
            }

        }
    }
}


