package com.example.atonce.presentation.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.component.app_bar_cards.TowIconCard
import com.example.atonce.presentation.profile.viewmodel.ProfileViewModel
import org.koin.compose.koinInject

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit = {},
    onWebViewClick: (title: String, url: String) -> Unit,
    modifier: PaddingValues,
    onDetailsClick :()-> Unit={},
    viewmodel: ProfileViewModel = koinInject(),
    onLogoutClicK:()-> Unit={}
) {
    val colors = MaterialTheme.colorScheme
    val user = viewmodel.userData
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(top=modifier.calculateTopPadding(), bottom = modifier.calculateBottomPadding())
    ) {
        TowIconCard(
            onStartIcon = Icons.AutoMirrored.Filled.ArrowBack,
            onEnIcon = Icons.Default.Language,
            onStartClick = {
                onBackClick()
            },
            onEndClick = {
            },
            headerTxt = stringResource(R.string.profile)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal =16.dp )
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
                        modifier = Modifier.clickable {  }
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
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
            modifier = Modifier.fillMaxWidth().padding(horizontal =16.dp )
        ) {
            Column {
                var title = stringResource(R.string.common_questions)
                ProfileItem(Icons.AutoMirrored.Filled.Help, title) {
                    onWebViewClick(title, "https://atonce2025.blogspot.com/2025/06/frequently-asked-questions-body-font.html")
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                title = stringResource(R.string.privacy_policy)
                ProfileItem(Icons.Default.Security, title) {
                    onWebViewClick(title, "https://atonce2025.blogspot.com/2025/06/privacy-policy-and-terms-body-font.html?m=1")
                }
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Call, stringResource(R.string.contact_us)) {}
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Info, stringResource(R.string.about_us)) {}
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.Default.Share, stringResource(R.string.share_app)) {}
                Divider(color = Color.LightGray, thickness = 1.dp)
                ProfileItem(Icons.AutoMirrored.Filled.Logout, stringResource(R.string.logout), isLogout = true) {
                    viewmodel.logOut()
                    onLogoutClicK()
                }
            }
        }
    }
}

@Composable
fun ProfileItem(icon: ImageVector, title: String, isLogout: Boolean = false, onClick: () -> Unit) {
   val colors= MaterialTheme.colorScheme
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
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier.size(20.dp)
        )
    }
}

