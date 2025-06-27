package com.example.atonce.presentation.orders.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coronavirus
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.core.extensions.convertNumbersToArabic
import com.example.atonce.domain.entity.OrderEntity
import com.example.atonce.presentation.common.FontSizes.SUB_ORDER_PRICE
import com.example.atonce.presentation.common.FontSizes.SUB_ORDER_TITLE


@Composable
fun OrderSubCard(item: OrderEntity.OrderEntityItem ){
    val colors = MaterialTheme.colorScheme
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 4.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(3f)
        ){
            Icon(
                imageVector = Icons.Filled.Coronavirus,
                contentDescription = ""

            )
            Text(
                text = item.medicineName,
                fontSize = SUB_ORDER_TITLE.sp,
                modifier = Modifier.padding(start = 8.dp),
                color= colors.onBackground,
                fontWeight = FontWeight.Bold


            )
        }
        Text(
            text = item.medicineCount.convertNumbersToArabic()+ stringResource(R.string.items),
            fontSize = SUB_ORDER_TITLE.sp,
            modifier = Modifier.weight(1f),
            color= colors.onBackground,
            fontWeight = FontWeight.Bold



        )
        Spacer(Modifier.weight(0.3f))
        Text(
            text = "${item.medicinePrice}".convertNumbersToArabic()+stringResource(R.string.egp),
            fontSize = SUB_ORDER_PRICE.sp,
            color =colors.primary ,
            modifier = Modifier.weight(1.15f),
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Bold

        )
        Spacer(Modifier.weight(0.1f))

    }

}