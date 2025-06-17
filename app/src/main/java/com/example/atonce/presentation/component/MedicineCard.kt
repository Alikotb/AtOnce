package com.example.atonce.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.comon.FontSizes.MEDICINE_CARD_MAIN_FONT
import com.example.atonce.presentation.comon.FontSizes.MEDICINE_CARD_MAIN_PRICE


@Preview(showBackground = true)
@Composable
fun MedicineCard(){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp

    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xffFBFBFB)
        ),
        elevation = CardDefaults.cardElevation(  defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box (
                modifier = Modifier
                    .width((screenWidth * 0.4).dp)
                    .height((screenHeight * 0.125).dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(16.dp)),

            ){
                Image(
                    painter = painterResource(R.drawable.medicin_card_img),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Row {
                Text(
                    text = "Panadol Hamada",
                    fontSize = MEDICINE_CARD_MAIN_FONT.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                )
                Spacer(Modifier.weight(1f))
            }
            Row {
                Text(
                    text = "Discount : 26%",
                    fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
                    modifier = Modifier.padding(start = 12.dp),
                    color = Color(0xFF1A998E)
                )
                Spacer(Modifier.weight(1f))
            }
            Row {
                Text(
                    text = "Price : 21.76 EGP",
                    fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
                    modifier = Modifier.padding(start = 12.dp)
                )
                Spacer(Modifier.weight(1f))
            }
            CustomCartBtn()

        }
    }


}