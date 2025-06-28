package com.example.atonce.presentation.forgotpassword.view.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.atonce.presentation.common.component.OtpIView

@Preview
@Composable
fun OtpScreenPreview() {
    OtpScreen()
}

@Composable
fun OtpScreen(
    isLoading: Boolean = false,
    email: String = "example@otp.com",
    onBackClick: () -> Unit = {},
    onSubmitClick: (String) -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme
    var code by remember { mutableStateOf(List(5) { "" }) }
    val focusRequesters = remember { List(5) { FocusRequester() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(24.dp)
    ) {
        OneIconCard(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            headerTxt = "",
            onClick = { onBackClick() },
            titleSize = 14
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.check_your_email),
            fontFamily = MediumFont,
            fontSize = 18.sp,
            color = colors.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.reset_link, email),
            fontFamily = RegularFont,
            fontSize = 12.sp,
            color = colors.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        OtpIView(
            digitsCount = 5,
            onComplete = { enteredCode ->
                onSubmitClick(enteredCode)
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = { onSubmitClick(code.joinToString("")) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = Color.White
            ),
            enabled = !isLoading
        ) {
            if (isLoading) {
                DotLoadingIndicator()
            } else {
                Text(
                    text = stringResource(R.string.verify_code),
                    fontFamily = MediumFont,
                    fontSize = LOGINBTN.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.haven_t_got_the_email_yet),
                fontFamily = RegularFont,
                fontSize = 12.sp,
                color = colors.onSurfaceVariant
            )
            Text(
                text = stringResource(R.string.resend_email),
                fontFamily = RegularFont,
                fontSize = 12.sp,
                color = colors.primary,
                modifier = Modifier.clickable {  }
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }
}



