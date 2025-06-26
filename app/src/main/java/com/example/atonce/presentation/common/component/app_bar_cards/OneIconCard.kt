package com.example.atonce.presentation.common.component.app_bar_cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.atonce.R
import com.example.atonce.core.enums.LanguageEnum
import com.example.atonce.presentation.common.FontSizes.TITLE
import com.example.atonce.presentation.common.component.TapBarBtn
import com.example.atonce.presentation.home.view.CircularIconButton
import com.example.atonce.presentation.common.theme.BoldFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.common.theme.WhiteColor


@Preview(showBackground = true)
@Composable
fun OneIconCard(
    onClick: () -> Unit = {},
    headerTxt: String = stringResource(R.string.home_screen),
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    titleSize: Int = TITLE
) {
    val config = LocalConfiguration.current
    val screenWidth = config.screenWidthDp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((screenWidth * 0.175).dp)
    ) {
        TapBarBtn(onIconClick = { onClick() }, icon = icon)
        Text(
            text = headerTxt,
            fontSize = titleSize.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = SemiBoldFont
        )

    }

}

@Preview(showBackground = true)
@Composable
fun NoIconCard(headerTxt: String = stringResource(R.string.orders_screen)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 24.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Text(
            text = headerTxt,
            fontSize = TITLE.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = BoldFont
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
fun TowIconCard(
    onStartClick: () -> Unit = {},
    onEndClick: () -> Unit = {},
    headerTxt: String = stringResource(R.string.home_screen),
    onStartIcon: ImageVector = Icons.Default.Call,
    onEnIcon: ImageVector = Icons.Default.Person,
    isProfile: Boolean = false,
    onLanguageClick: (String) -> Unit = {}
) {
    var expanded = remember { mutableStateOf(false) }
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularIconButton(
            icon = onStartIcon,
            onClick = { onStartClick() },
            tint = colors.onBackground
        )
        Text(
            text = headerTxt,
            fontSize = TITLE.sp,
            color = colors.onBackground,
            fontFamily = BoldFont
        )
        Box {
            CircularIconButton(
                icon = onEnIcon,
                onClick = {
                    onEndClick()
                     expanded.value =!expanded.value
                },
                tint = colors.onBackground
            )
            if(isProfile) {
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    listOf(
                        LanguageEnum.getValue(LanguageEnum.ENGLISH.code),
                        LanguageEnum.getValue(LanguageEnum.ARABIC.code),
                        LanguageEnum.getValue(LanguageEnum.DEFAULT.code),
                        ).forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                expanded.value = false
                                onLanguageClick(LanguageEnum.geCodeByValue(option))
                            },
                            leadingIcon = {
                                val icon = when (LanguageEnum.geCodeByValue(option)) {
                                    LanguageEnum.ENGLISH.code -> painterResource(R.drawable.uk)
                                    LanguageEnum.ARABIC.code -> painterResource(R.drawable.egypt)
                                    LanguageEnum.DEFAULT.code -> painterResource(R.drawable.system)
                                    else -> null
                                }
                                icon?.let {
                                    Icon(
                                        painter = it,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}