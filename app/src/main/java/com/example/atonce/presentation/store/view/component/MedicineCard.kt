package com.example.atonce.presentation.store.view.component

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.core.extensions.convertNumbersToArabic
import com.example.atonce.presentation.common.FontSizes.MEDICINE_CARD_MAIN_FONT
import com.example.atonce.presentation.common.FontSizes.MEDICINE_CARD_MAIN_PRICE
import com.example.atonce.presentation.common.component.CustomCartBtn
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.store.model.WarehouseMedicines
import java.util.Locale


@Composable
fun MedicineCard(
    obj: WarehouseMedicines,
    onClick: () -> Unit = {},
    enabled: Boolean = true
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    val colors = MaterialTheme.colorScheme
    val languageFlag = if ( Locale.getDefault().language == "ar") true else false

    Card(
        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surface
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
                    text = if(languageFlag)obj.arabicMedicineName else obj.englishMedicineName,
                    fontSize = MEDICINE_CARD_MAIN_FONT.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp)
                )
                Spacer(Modifier.weight(1f))
            }
            Row {
                Text(
                    text = "${stringResource(R.string.discount)} ${obj.medicineDiscount}%".convertNumbersToArabic(),
                    fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
                    modifier = Modifier.padding(start = 12.dp),
                    color = colors.primary,
                    fontFamily = SemiBoldFont
                )
                Spacer(Modifier.weight(1f))
            }
            Row {
                Text(
                    text = stringResource(R.string.price)+String.format("%.2f", obj.medicineFinalPrice.toDouble()).convertNumbersToArabic()+ stringResource(R.string.egp),
                    fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
                    modifier = Modifier.padding(start = 12.dp),
                    fontFamily = RegularFont
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${ obj.medicinePrice.toInt()}".convertNumbersToArabic(),
                    fontSize = MEDICINE_CARD_MAIN_PRICE.sp,
                    color = Color.Red,
                    fontFamily = RegularFont,
                    textDecoration = TextDecoration.LineThrough
                )

                Spacer(Modifier.weight(1f))
            }
            CustomCartBtn(
                onClick = {
                    onClick()
                },
                enabled = enabled
            )

        }
    }


}