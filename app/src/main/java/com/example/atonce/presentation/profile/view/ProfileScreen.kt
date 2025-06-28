package com.example.atonce.presentation.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MedicalInformation
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.core.content.ContextCompat.startActivity
import com.example.atonce.R
import com.example.atonce.core.constants.AppConstants
import com.example.atonce.core.constants.AppConstants.COMMON_QUESTIONS_URL
import com.example.atonce.core.constants.AppConstants.CONTACT_US_URL
import com.example.atonce.core.constants.AppConstants.PRIVACY_AND_POLICY_URL
import com.example.atonce.core.extensions.restartActivity
import com.example.atonce.presentation.common.component.AlertDialogExample
import com.example.atonce.presentation.common.component.AppDialog
import com.example.atonce.presentation.common.component.ShareApp
import com.example.atonce.presentation.common.component.app_bar_cards.TowIconCard
import com.example.atonce.presentation.common.theme.DarkWhiteColor
import com.example.atonce.presentation.common.theme.PrimaryColor
import com.example.atonce.presentation.common.theme.WhiteColor
import com.example.atonce.presentation.profile.viewmodel.ProfileViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onWebViewClick: (title: String, url: String) -> Unit,
    modifier: PaddingValues,
    onDetailsClick: () -> Unit = {},
    viewmodel: ProfileViewModel = koinInject(),
    onLogoutClicK: () -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    val user = viewmodel.userData
    var expanded = remember { mutableStateOf(false) }
    val openAlertDialog = remember { mutableStateOf(false) }
    var showLogoutDialog by remember { mutableStateOf(false) }

    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(
                top = modifier.calculateTopPadding(),
                bottom = modifier.calculateBottomPadding()
            )
    ) {
        val darkTheme = isSystemInDarkTheme()
        val systemUiController = rememberSystemUiController()

        SideEffect {
            systemUiController.setStatusBarColor(
                color = if (darkTheme) DarkWhiteColor else WhiteColor,
                darkIcons = !darkTheme
            )
        }


        TowIconCard(
            onStartIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onEnIcon = Icons.Default.Language,
            onStartClick = {
                onBackClick()
            },
            onEndClick = {
                expanded.value = !expanded.value
            },
            headerTxt = stringResource(R.string.profile),
            isProfile = true,
            onLanguageClick = { code ->
                viewmodel.changeLanguage(code)
                ctx.restartActivity()
            }
        )


        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(onClick = {
                    onDetailsClick()
                })
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pharmacy),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        user.userName,
                        fontSize = 18.sp,
                        color = colors.onBackground,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        stringResource(R.string.see_account_details),
                        fontSize = 14.sp,
                        color = colors.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable {
                            onDetailsClick()
                        }
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Details",
                    tint = colors.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column {
                ProfileItem(Icons.AutoMirrored.Filled.Help, ctx.getString(R.string.common_questions)) {
                    onWebViewClick(
                        ctx.getString(R.string.common_questions),
                        COMMON_QUESTIONS_URL
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Security, ctx.getString(R.string.privacy_policy)) {
                    onWebViewClick(
                       ctx.getString(R.string.privacy_policy),
                        PRIVACY_AND_POLICY_URL
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Call, stringResource(R.string.contact_us)) {
                    onWebViewClick(
                        ctx.getString(R.string.contact_us),
                        CONTACT_US_URL
                    )
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Info, stringResource(R.string.about_us)) {
                    openAlertDialog.value=true

                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Share, stringResource(R.string.share_app)) {

                    startActivity(ctx, ShareApp(AppConstants.SHARE_APP_MESSAGE), null)

                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(
                    Icons.AutoMirrored.Filled.Logout,
                    stringResource(R.string.logout),
                    isLogout = true
                ) {
                    showLogoutDialog = true

                }
            }
        }
    }
    if (showLogoutDialog) {
        AppDialog(
            title = stringResource(R.string.logout),
            message = stringResource(R.string.are_you_sure_you_want_to_logout),
            confirmText = stringResource(R.string.logout),
            onDismiss = { showLogoutDialog = false },
            onConfirm = {
                showLogoutDialog = false
                viewmodel.logOut()
                onLogoutClicK()
            }
        )
    }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    openAlertDialog.value = false
                },
                dialogTitle = AppConstants.ABOUT_US_TITLE,
                dialogText = AppConstants.ABOUT_US_MESSAGE,
                icon = Icons.Filled.MedicalInformation
            )
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, title: String, isLogout: Boolean = false, onClick: () -> Unit) {
    val colors = MaterialTheme.colorScheme
    val textColor = if (isLogout) Color.Red else colors.onBackground
    val iconColor = if (isLogout) Color.Red else Color(0xFF1A998E)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(24.dp))

        Text(
            text = title,
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier.size(20.dp)
        )
    }
}


