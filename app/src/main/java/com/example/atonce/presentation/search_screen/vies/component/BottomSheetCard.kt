package com.example.atonce.presentation.search_screen.vies.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.MEDICINE_DISCOUNT
import com.example.atonce.presentation.common.FontSizes.PHARMA_NAME
import com.example.atonce.presentation.common.component.CustomCartBtn


@Preview(showBackground = true)
@Composable
fun BottomSheetCard() {
    val colors =MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.pharmacy),
                    contentDescription = "Medicine name",
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
                Spacer(Modifier.height(48.dp))
            }
            Column(
                modifier = Modifier.weight(3.5f)
            ) {
                Text(
                    text = "UM Pharma UM Pharma UM  ",
                    fontSize = PHARMA_NAME.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${stringResource(R.string.discount)} 28 %",
                    fontSize = MEDICINE_DISCOUNT.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    color = colors.primary
                )
                Text(
                    text = stringResource(R.string.price)+"21.76"+ stringResource(R.string.egp),
                    fontSize = MEDICINE_DISCOUNT.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Spacer(Modifier.weight(1f))
                    CustomCartBtn(onClick = {})
                }
            }
        }
    }
}

