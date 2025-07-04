package com.example.atonce.presentation.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.presentation.common.FontSizes.FORGOTPASS
import com.example.atonce.presentation.common.FontSizes.LOGINBTN
import com.example.atonce.presentation.common.FontSizes.REGISTERHERE
import com.example.atonce.presentation.common.component.LoginPasswordTF
import com.example.atonce.presentation.common.component.LoginTF
import com.example.atonce.presentation.common.theme.BoldFont
import com.example.atonce.presentation.common.theme.MediumFont
import com.example.atonce.presentation.common.theme.PrimaryColor
import com.example.atonce.presentation.common.theme.RegularFont
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.common.component.DotLoadingIndicator
import com.example.atonce.presentation.login.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    snackBarHostState: SnackbarHostState,
    onForgotPasswordClick: () -> Unit = {},
    modifier: PaddingValues,
    viewModel: LoginViewModel = koinViewModel()
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val colors = MaterialTheme.colorScheme

    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loginSuccess.collect {
            onLoginClick()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            snackBarHostState.showSnackbar(message)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = modifier.calculateTopPadding())
            .background(colors.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            Modifier.height((screenHeight * 0.06).dp)
        )

        Image(
            painter = painterResource(R.drawable.pharmacy),
            modifier = Modifier
                .size((screenWidth * 0.25).dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit,
            contentDescription = "App Logo",
            )

       /* Text(
            text = stringResource(R.string.welcome_back_login_now),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )*/

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            stringResource(R.string.welcome_back), fontSize = 20.sp, color = PrimaryColor,
            fontFamily = BoldFont
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            stringResource(R.string.to_continue_login_now),
            fontSize = 12.sp,
            fontFamily = RegularFont
        )

        Spacer(modifier = Modifier.height(32.dp))


        LoginTF(onValueChange = {
            email = it
        })
        Spacer(
            Modifier.height((screenHeight * 0.01).dp)
        )
        LoginPasswordTF(onValueChange = {
            password = it
        })
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {
                    onForgotPasswordClick()
                }
            ) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    fontSize = FORGOTPASS.sp,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

        }
        Spacer(
            Modifier.height((screenHeight * 0.01).dp)
        )
        Button(
            shape = RoundedCornerShape(8.dp),
            onClick = {
                viewModel.login(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = Color.White
            ),
            enabled = !isLoading
        ) {
            if (isLoading) {
                DotLoadingIndicator()
            }else {
                Text(
                    text = stringResource(R.string.login),
                    fontFamily = MediumFont,
                    fontSize = LOGINBTN.sp
                )
            }
        }
        Spacer(
            Modifier.height((screenHeight * 0.01).dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                text = stringResource(R.string.don_t_have_an_account_yet),
                fontSize = REGISTERHERE.sp,
                fontFamily = MediumFont,
            )
            TextButton(
                onClick = { onRegisterClick() }
            ) {
                Text(
                    text = stringResource(R.string.register_here),
                    fontSize = REGISTERHERE.sp,
                    color = colors.primary,
                    fontFamily = SemiBoldFont,
                )
            }
            Spacer(Modifier.width(4.dp))
        }
    }
}

