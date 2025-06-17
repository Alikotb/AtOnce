package com.example.atonce.presentation.search_screen.vies.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.comon.FontSizes.MEDICINE_DISCOUNT
import com.example.atonce.presentation.comon.FontSizes.PHARMA_NAME
import com.example.atonce.presentation.component.CustomCartBtn
import com.example.atonce.presentation.theme.Til


@Preview(showBackground = true)
@Composable
fun SearchCard(onCartClick: () -> Unit = {}, onSuppliersClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colors.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),

                ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp, top = 4.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pharmacy),
                        contentDescription = "Medicine name",
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Column(
                    modifier = Modifier.weight(4f)
                ) {
                    Text(
                        text = "UM Pharma UM Pharma UM Pharma ",
                        fontSize = PHARMA_NAME.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Discount : 28 %",
                        fontSize = MEDICINE_DISCOUNT.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                        color = Til
                    )
                    Text(
                        text = "Price : 21.76 EGP",
                        fontSize = MEDICINE_DISCOUNT.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                CustomCartBtn(onClick = {
                    onCartClick()
                })
                CustomCartBtn(
                    msg = "All Suppliers",
                    imageVictor = Icons.Filled.Store,
                    color = Color(0xff403C3D),
                    onClick ={
                        onSuppliersClick()
                    }
                )
            }
        }
    }
}
