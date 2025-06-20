package com.example.atonce.presentation.common.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.MEDICINE_CARD_MAIN_PRICE
import com.example.atonce.presentation.common.theme.Til


@Composable
fun CustomCartBtn(onClick : ()->Unit = {}, imageVictor: ImageVector = Icons.Filled.ShoppingCart, msg: String = stringResource(
    R.string.add_to_cart
), color:Color = Til
){
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors =ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.surface
        )
    ){
        Text(
            text = msg,
            color = MaterialTheme.colorScheme.surface,
            fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Icon(
            imageVector = imageVictor,
            contentDescription = "",

            )
    }
}