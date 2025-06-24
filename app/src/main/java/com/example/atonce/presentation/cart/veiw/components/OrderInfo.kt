package com.example.atonce.presentation.cart.veiw.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.common.theme.WhiteColor


@Composable
fun OrderInfo(
    subtotal: Double,
    discount: Double,
    total: Double,
    minimum: Double,
    onCheckout: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stringResource(R.string.subtotal), fontFamily = RegularFont, fontSize = 12.sp, color = colors.onSurfaceVariant)
            Text(text = stringResource(R.string.egp_cart, "%.2f".format(subtotal)), fontFamily = RegularFont, fontSize = 12.sp, color = colors.onSurfaceVariant)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.discount_info), fontFamily = RegularFont, fontSize = 12.sp, color = colors.onSurfaceVariant)
            Text(text = stringResource(R.string.egp_cart, "%.2f".format(discount)), fontFamily = RegularFont, fontSize = 12.sp, color = colors.onSurfaceVariant)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(id = R.string.total), fontFamily = SemiBoldFont, fontSize = 14.sp, color = colors.onSurfaceVariant, fontWeight = FontWeight.Bold)
            Text(text = stringResource(R.string.egp_cart, "%.2f".format(total)), fontFamily = RegularFont, fontSize = 14.sp, color = colors.onSurfaceVariant, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            stringResource(R.string.minimum_to_place_an_order_is_egp, minimum),
            fontFamily = RegularFont,
            fontSize = 14.sp,
            color = Color.Red,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 4.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colors.primary),
            enabled = total >= 70,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                stringResource(R.string.checkout_egp, "%.2f".format(total)),
                fontFamily = SemiBoldFont,
                fontSize = 16.sp,
                color = WhiteColor
            )
        }
    }
}