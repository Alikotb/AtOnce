package com.example.atonce.presentation.search_screen.vies.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.comon.FontSizes.MEDICINE_BTN_SHEET_NAME


@ExperimentalMaterial3Api
@Composable
fun ModelSheet(onClose : () -> Unit={}){
    val sheetState = rememberModalBottomSheetState()
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp
    ModalBottomSheet(
        onDismissRequest = {
            onClose()
        },
        sheetState = sheetState,
        containerColor = Color(0xffF5F5F5),
        scrimColor = Color.Black.copy(alpha = 0.2f)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = (screenHeight*.35).dp, max = (screenHeight*.75).dp)
                .padding(bottom = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp)),
                ) {
                    Image(
                        painter = painterResource(R.drawable.medicin_card_img),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = "Panadol Extra 600mg ",
                    fontSize = MEDICINE_BTN_SHEET_NAME.sp,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Divider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 12.dp))
            LazyColumn(modifier = Modifier  .weight(1f).padding(horizontal = 16.dp).padding(vertical = 12.dp)) {
                items(10) {
                    BottomSheetCard()
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}