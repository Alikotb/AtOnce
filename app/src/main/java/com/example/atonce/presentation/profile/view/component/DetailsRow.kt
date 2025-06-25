package com.example.atonce.presentation.profile.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.Til


@Composable
fun DetailsRow(
    txt: String ="Ali Kotb Mohamed",
    icon:ImageVector = Icons.Outlined.Phone
){
    val colors = MaterialTheme.colorScheme
    Row (
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Box (
            modifier = Modifier.size(32.dp)
                .background(color = colors.primary.copy(alpha = 0.25f)),
            contentAlignment = Alignment.Center
        ){
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = colors.primary,

            )

        }
        Text(
            text = txt,
            fontSize = 14.sp,
            color = colors.onBackground,
            fontFamily = RegularFont

        )
    }
}