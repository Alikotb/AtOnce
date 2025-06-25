package com.example.atonce.presentation.cart.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.domain.entity.CartItemEntity
import com.example.atonce.presentation.common.theme.MediumFont
import com.example.atonce.presentation.common.theme.RedColor
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import java.util.Locale

@Composable
fun AddToCartCard(
    cartItem: CartItemEntity,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onDelete: () -> Unit,
    enapled: Boolean
) {
    val colors = MaterialTheme.colorScheme
    val totalCost = cartItem.priceAfterDiscount * cartItem.quantity
    val medicationName = if (Locale.getDefault().language == "ar") cartItem.arabicMedicineName
    else cartItem.englishMedicineName

    val costPerItem = cartItem.priceBeforeDiscount
    val discountPercent = cartItem.discount
    val quantity = cartItem.quantity

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = colors.surface)
    ) {

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ads1),
                contentDescription = "medicationName",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),

                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                verticalArrangement = Arrangement.Top,
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = medicationName,
                        fontFamily = SemiBoldFont,
                        fontSize = 16.sp,
                        color = colors.onSurface,
                    )
                    IconButton(onClick = onDelete) {
                        Icon(
                            painter = painterResource(id = R.drawable.trash),
                            contentDescription = "Delete",
                            tint = RedColor,
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.cart_discount, discountPercent),
                    fontFamily = MediumFont,
                    fontSize = 14.sp,
                    color = colors.primary
                )
                Text(
                    text = stringResource(R.string.cost_per_item_egp, costPerItem),
                    fontFamily = RegularFont,
                    fontSize = 13.sp,
                    color = colors.onSurfaceVariant
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp , end = 8.dp , bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.total_egp, String.format("%.2f", totalCost)),
                fontFamily = MediumFont,
                fontSize = 14.sp,
                color = RedColor
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .border(1.dp, colors.primary, RoundedCornerShape(16.dp))
            ) {
                IconButton(
                    enabled = enapled,
                    onClick = onDecrease) {
                    Icon(Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text(
                    "$quantity",
                    fontFamily = MediumFont,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                IconButton(
                    enabled = enapled,
                    onClick = onIncrease) {
                    Icon(Icons.Default.Add, contentDescription = "Increase")
                }
            }


        }

    }
}
