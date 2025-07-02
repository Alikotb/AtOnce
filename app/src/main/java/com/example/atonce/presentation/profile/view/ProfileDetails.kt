package com.example.atonce.presentation.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Warehouse
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.theme.DarkWhiteColor
import com.example.atonce.presentation.common.theme.PrimaryColor
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.common.theme.WhiteColor
import com.example.atonce.presentation.profile.view.component.DetailsRow
import com.example.atonce.presentation.profile.viewmodel.ProfileViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@Composable
fun ProfileDetails(
    modifier: PaddingValues = PaddingValues(),
    onClick: () -> Unit = {},
    viewModel: ProfileViewModel = koinViewModel()
) {
    val colors = MaterialTheme.colorScheme
    val config = LocalConfiguration.current
    val screenHeight = config.screenHeightDp
    val user = viewModel.userData
    Column {
        val darkTheme = isSystemInDarkTheme()
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = if (darkTheme) colors.onSecondary else PrimaryColor,
                darkIcons = false
            )
        }

        Box(
            modifier = Modifier
                .background(colors.onSecondary)
                .padding(top = modifier.calculateTopPadding()),

            ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {
                    onClick()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "nill",
                        tint = WhiteColor
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    text = "My Profile",
                    fontFamily = SemiBoldFont,
                    fontSize = 18.sp,
                    color = WhiteColor

                )
                Spacer(Modifier.weight(1f))
                Spacer(Modifier.weight(1f))

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = (screenHeight * 0.23).dp, bottom = (screenHeight * 0.2).dp)
                    .padding(horizontal = 16.dp)
                    .border(
                        width = 2.dp,
                        color = colors.onTertiary,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .background(
                        color = colors.onTertiary,
                        shape = RoundedCornerShape(30.dp)
                    ),
            ) {
                Column(
                    Modifier.fillMaxSize()
                ) {
                    Text(
                        text = user.userName,
                        fontFamily = SemiBoldFont,
                        fontSize = 16.sp,
                        color = colors.primary,
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                    DetailsRow(txt = user.email, icon = Icons.Filled.Email)
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                    DetailsRow(txt = user.phoneNumber)
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                    DetailsRow(txt = user.pharmacyName, Icons.Filled.Warehouse)
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                    DetailsRow(txt = user.governorate, Icons.Filled.LocationOn)
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                    DetailsRow(txt = user.address, Icons.Filled.LocationOn)
                    Spacer(Modifier.height(((screenHeight * 0.03).dp)))
                }
            }
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (screenHeight * 0.14).dp)
                    .border(width = 3.dp, color = colors.onSecondary, shape = CircleShape)
                    .clip(CircleShape)
                    .background(color = colors.outlineVariant.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.avatar_design),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}