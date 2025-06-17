package com.example.atonce.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.theme.RegularFont
import com.example.atonce.presentation.theme.SemiBoldFont


@Composable
fun OrderInfo(
    subtotal: Double,
    discount: Double,
    total: Double,
    onCheckout: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Order Info",
            fontFamily = SemiBoldFont,
            fontSize = 20.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("SubTotal", fontFamily = RegularFont, fontSize = 16.sp, color = Color.Gray)
            Text("${"%.2f".format(subtotal)} EGP", fontFamily = RegularFont, fontSize = 16.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Discount", fontFamily = RegularFont, fontSize = 16.sp, color = Color.Gray)
            Text("-${"%.2f".format(discount)} EGP", fontFamily = RegularFont, fontSize = 16.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Total", fontFamily = SemiBoldFont, fontSize = 16.sp, color = Color(0xFF666666))
            Text(
                "${"%.2f".format(total)} EGP",
                fontFamily = SemiBoldFont,
                fontSize = 16.sp,
                color = Color(0xFF666666)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Minimum to place an order is 70 EGP",
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
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A998E)),
            enabled = total >= 70,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                "Checkout ${"%.2f".format(total)} EGP",
                fontFamily = SemiBoldFont,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}