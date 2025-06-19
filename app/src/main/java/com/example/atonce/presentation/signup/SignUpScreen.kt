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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce.R
import com.example.atonce.presentation.component.app_bar_cards.OneIconCard
import com.example.atonce.presentation.signup.components.CustomDropdownMenu
import com.example.atonce.presentation.signup.components.CustomPasswordField
import com.example.atonce.presentation.signup.components.CustomTextField
import com.example.atonce.presentation.theme.PrimaryColor
import com.example.atonce.presentation.theme.SemiBoldFont
import com.example.atonce.presentation.theme.WhiteColor

@Composable
fun SignUpScreen(
    onBackClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: PaddingValues
) {
    val colors = MaterialTheme.colorScheme

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
//                stringResource(R.string.register_new_account),
//    Icons.AutoMirrored.Filled.ArrowBack,

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.onPrimary)
            .padding(horizontal = 24.dp)
            .padding(top =modifier.calculateTopPadding(), bottom = modifier.calculateBottomPadding())
            .verticalScroll(rememberScrollState())
    ) {
        OneIconCard(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            headerTxt = stringResource(R.string.register_new_account),
            onClick = {
                onBackClick()
            }
        )
        CustomTextField(
            value = username,
            onValueChange = { username = it },
            label = stringResource(R.string.username),
            placeholder = stringResource(R.string.enter_your_username)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = pharmacyName,
            onValueChange = { pharmacyName = it },
            label = stringResource(R.string.pharmacy_name),
            placeholder = stringResource(R.string.enter_pharmacy_name)
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email_signup),
            placeholder = stringResource(R.string.enter_your_email),
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomPasswordField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password_signup),
            placeholder = stringResource(R.string.enter_your_password),
            visible = passwordVisible,
            onVisibilityChange = { passwordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomPasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = stringResource(R.string.confirm_password),
            placeholder = stringResource(R.string.re_enter_your_password),
            visible = confirmPasswordVisible,
            onVisibilityChange = { confirmPasswordVisible = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = phone,
            onValueChange = { phone = it },
            label = stringResource(R.string.phone),
            placeholder = stringResource(R.string.enter_phone_number),
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropdownMenu(
            value = governorate,
            onValueChange = { governorate = it },
            label = stringResource(R.string.governorate),
            options = governorates,
            expanded = governorateExpanded,
            onExpandedChange = { governorateExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropdownMenu(
            value = area,
            onValueChange = { area = it },
            label = stringResource(R.string.area),
            options = areas,
            expanded = areaExpanded,
            onExpandedChange = { areaExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = addressDetails,
            onValueChange = { addressDetails = it },
            label = stringResource(R.string.address_details),
            placeholder = stringResource(R.string.enter_full_address_details),
            singleLine = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = invitationCode,
            onValueChange = { invitationCode = it },
            label = stringResource(R.string.invitation_code),
            placeholder = stringResource(R.string.enter_invitation_code)
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
                text = stringResource(R.string.register),
                fontFamily = SemiBoldFont,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}




