package com.example.atonce.presentation.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.comon.FontSizes.FORGOTPASS
import com.example.atonce.presentation.comon.FontSizes.LOGINBTN
import com.example.atonce.presentation.comon.FontSizes.REGISTERHERE
import com.example.atonce.presentation.component.LoginPasswordTF
import com.example.atonce.presentation.component.LoginTF


@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val screenHeight = configuration.screenHeightDp
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val colors = MaterialTheme.colorScheme

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            Modifier.height((screenHeight * 0.085).dp)
        )
        Image(
            painter = painterResource(R.drawable.logo),
            modifier = Modifier
                .size((screenWidth * 0.4).dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit,
            contentDescription = "App Logo",

        )
        Text(
            text = "     Welcome to \nAt Once Pharma",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp, bottom = 32.dp)
        )
        LoginTF(onValueChange = {
            email = it
        })
        Spacer(
            Modifier.height((screenHeight * 0.02).dp)
        )
        LoginPasswordTF(onValueChange = {
            password = it
        })
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                onClick = {}
            ) {
                Text(
                    text = "Forgot Password ?",
                    fontSize = FORGOTPASS.sp,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

        }
        Spacer(
            Modifier.height((screenHeight * 0.02).dp)
        )
        Button(
            shape = RoundedCornerShape(12.dp),
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Login",
                fontWeight = FontWeight.Bold,
                fontSize = LOGINBTN.sp
            )
        }
        Spacer(
            Modifier.height((screenHeight * 0.01).dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.weight(1f))
            Text(
                text = "Don’t have an account yet ?",
                fontSize = REGISTERHERE.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 12.dp, start = 12.dp)
            )
            TextButton(
                onClick = {}
            ) {
                Text(
                    text = "Register here",
                    fontSize = REGISTERHERE.sp,
                    color = colors.primary,
                    fontWeight = FontWeight.Bold,
                )
            }


        }


    }

}