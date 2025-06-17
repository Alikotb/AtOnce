package com.example.atonce.presentation.splash


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.theme.WhiteColor

@Preview(showBackground = true)
@Composable
fun SplashScreen(
    navToHome: () -> Unit = {},
    navToLogin:  () -> Unit = {},
    navToSignUp: () -> Unit = {},
    navToOrders: () -> Unit = {},
    navToStore: () -> Unit = {},
    navToSearch: () -> Unit = {},
    navToProfile: () -> Unit = {},
    navToCart: () -> Unit = {}
                 ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        Spacer(Modifier.height(64.dp))
        Btn(onClick=navToHome)
        Spacer(Modifier.height(16.dp))
        Btn(onClick=navToLogin,txt = "Login")
        Spacer(Modifier.height(16.dp))
        Btn(txt ="SignUp", onClick = navToSignUp)
        Spacer(Modifier.height(16.dp))
        Btn(txt ="Orders", onClick = navToOrders)
        Spacer(Modifier.height(16.dp))
        Btn(txt ="Store", onClick = navToStore)
        Spacer(Modifier.height(16.dp))
        Btn(txt ="Search", onClick = navToSearch)
        Spacer(Modifier.height(16.dp))
        Btn(txt ="profile", onClick = navToProfile)
        Spacer(Modifier.height(16.dp))
        Btn(txt ="cart", onClick = navToCart)

    }

}

@Preview(showBackground = true)
@Composable
fun Btn(onClick: () -> Unit = {}, txt: String = "Home") {
    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 32.dp),
        shape = RoundedCornerShape(12.dp),
        colors =ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1A998E),
            contentColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = txt,
            color = WhiteColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold

        )
    }
}