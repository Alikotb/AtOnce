package com.example.atonce.presentation.signup


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.presentation.signup.components.CustomDropdownMenu
import com.example.atonce.presentation.signup.components.CustomPasswordField
import com.example.atonce.presentation.signup.components.CustomTextField
import com.example.atonce.presentation.theme.BlackColor
import com.example.atonce.presentation.theme.PrimaryColor
import com.example.atonce.presentation.theme.SemiBoldFont
import com.example.atonce.presentation.theme.WhiteColor

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    val governorates = listOf("Alexandria", "Cairo", "Giza")
    val areas = listOf("Agami, Alexandria", "Smouha, Alexandria", "Nasr City, Cairo")

    var username by remember { mutableStateOf("") }
    var pharmacyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var governorate by remember { mutableStateOf(governorates[0]) }
    var area by remember { mutableStateOf(areas[0]) }
    var addressDetails by remember { mutableStateOf("") }
    var invitationCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var governorateExpanded by remember { mutableStateOf(false) }
    var areaExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteColor)
            .padding(horizontal = 24.dp).padding(vertical =24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = BlackColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                "Register New Account",
                fontFamily = SemiBoldFont,
                fontSize = 18.sp,
                color = BlackColor,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(48.dp))
        }

        CustomTextField(
            value = username,
            onValueChange = { username = it },
            label = "Username",
            placeholder = "Enter your username"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = pharmacyName,
            onValueChange = { pharmacyName = it },
            label = "Pharmacy Name",
            placeholder = "Enter pharmacy name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "Enter your email",
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomPasswordField(
            value = password,
            onValueChange = { password = it },
            label = "Password",
            placeholder = "Enter your password",
            visible = passwordVisible,
            onVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomPasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirm Password",
            placeholder = "Re-enter your password",
            visible = confirmPasswordVisible,
            onVisibilityChange = { confirmPasswordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Phone",
            placeholder = "Enter phone number",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropdownMenu(
            value = governorate,
            onValueChange = { governorate = it },
            label = "Governorate",
            options = governorates,
            expanded = governorateExpanded,
            onExpandedChange = { governorateExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropdownMenu(
            value = area,
            onValueChange = { area = it },
            label = "Area",
            options = areas,
            expanded = areaExpanded,
            onExpandedChange = { areaExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addressDetails,
            onValueChange = { addressDetails = it },
            label = "Address Details",
            placeholder = "Enter full address details",
            singleLine = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = invitationCode,
            onValueChange = { invitationCode = it },
            label = "Invitation Code",
            placeholder = "Enter invitation code"
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                onRegisterClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor,
                contentColor = WhiteColor
            )
        ) {
            Text(
                "Register",
                fontFamily = SemiBoldFont,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}




