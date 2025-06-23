package com.example.atonce.presentation.search.view.component

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.atonce.R
import com.example.atonce.domain.entity.Medicine
import com.example.atonce.presentation.common.FontSizes.MEDICINE_DISCOUNT
import com.example.atonce.presentation.common.FontSizes.PHARMA_NAME
import com.example.atonce.presentation.common.component.CustomCartBtn


@Composable
fun SearchCard(medicine: Medicine,
               onCartClick: () -> Unit = {},
               onSuppliersClick: (Medicine) -> Unit = {}) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
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
                    AsyncImage(
                        model = medicine.imageUrl,
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
                        text = medicine.medicineName,
                        fontSize = PHARMA_NAME.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text ="${stringResource(R.string.discount)} ${medicine.maximumDiscount} %",
                        fontSize = MEDICINE_DISCOUNT.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                        color = colors.primary
                    )
                    Text(
                        text = stringResource(R.string.price)+"${medicine.price}"+ stringResource(R.string.egp),
                        fontSize = MEDICINE_DISCOUNT.sp,
                        modifier = Modifier.padding(start = 12.dp, top = 4.dp),
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                CustomCartBtn(
                    color = colors.primary,
                    onClick = {
                    onCartClick()
                })
                CustomCartBtn(
                    msg = stringResource(R.string.all_suppliers),
                    imageVictor = Icons.Filled.Store,
                    color =colors.surfaceDim,
                    onClick ={
                        onSuppliersClick(medicine)
                    }
                )
            }
        }
    }
}
