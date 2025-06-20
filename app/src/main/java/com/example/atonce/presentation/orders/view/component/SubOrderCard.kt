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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.SUB_ORDER_PRICE
import com.example.atonce.presentation.common.FontSizes.SUB_ORDER_TITLE


@Preview(showBackground = true)
@Composable
fun OrderSubCard(item : Triple<String, String, String> = Triple("Panadol Extra 600mg","3","150")){
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
        Spacer(Modifier.weight(0.275f))
        Text(
            text = item.second+ stringResource(R.string.items),
            fontSize = SUB_ORDER_TITLE.sp,
            modifier = Modifier.weight(1f)

        )
        Spacer(Modifier.weight(0.3f))
        Text(
            text = item.third+stringResource(R.string.egp),
            fontSize = SUB_ORDER_PRICE.sp,
            color =colors.primary ,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }

}