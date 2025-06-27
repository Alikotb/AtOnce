package com.example.atonce.presentation.forgotpassword.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.LOGINBTN
import com.example.atonce.presentation.common.component.DotLoadingIndicator
import com.example.atonce.presentation.common.component.app_bar_cards.OneIconCard
import com.example.atonce.presentation.common.theme.MediumFont
import com.example.atonce.presentation.common.theme.PrimaryColor
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.WhiteColor
import com.example.atonce.presentation.signup.components.CustomTextField


@Preview
@Composable
fun EmailScreenPreview() {
    EmailScreen()
}

@Composable
fun EmailScreen(
    isLoading: Boolean = false,
    onBackClick: () -> Unit = {},
    onSubmitClick: (String) -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(24.dp)
    ) {
        OneIconCard(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            headerTxt = "",
            onClick = {
                onBackClick()
            },
            titleSize = 14
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.forgot_password),
            fontFamily = MediumFont,
            fontSize = 18.sp,
            color = colors.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.please_enter_your_email_to_reset_the_password),
            fontFamily = RegularFont,
            fontSize = 12.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.your_email),
            placeholder = stringResource(R.string.your_email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onSubmitClick(email)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = WhiteColor
            ),
            enabled = !isLoading
        ) {
            if (isLoading) {
                DotLoadingIndicator()
            }else {
                Text(
                    text = stringResource(R.string.reset_password),
                    fontFamily = MediumFont,
                    fontSize = LOGINBTN.sp
                )
            }
        }
    }
}
