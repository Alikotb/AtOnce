package com.example.atonce.presentation.signup


import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.R
import com.example.atonce.domain.Response
import com.example.atonce.presentation.QRCodeScannerActivity
import com.example.atonce.presentation.common.component.LoadingView
import com.example.atonce.presentation.common.component.NoInternet
import com.example.atonce.presentation.common.component.app_bar_cards.OneIconCard
import com.example.atonce.presentation.signup.components.CustomDropdownMenu
import com.example.atonce.presentation.signup.components.CustomPasswordField
import com.example.atonce.presentation.signup.components.CustomTextField
import com.example.atonce.presentation.signup.components.CustomTextFieldWithIcon
import com.example.atonce.presentation.common.theme.PrimaryColor
import com.example.atonce.presentation.common.theme.SemiBoldFont
import com.example.atonce.presentation.common.theme.WhiteColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
    modifier: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    val colors = MaterialTheme.colorScheme

    val governorates by viewModel.governorates.collectAsStateWithLifecycle()
    val areas by viewModel.areas.collectAsStateWithLifecycle()

    val registerState by viewModel.registerState.collectAsStateWithLifecycle()

    var username by remember { mutableStateOf("") }
    var pharmacyName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var governorate by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var addressDetails by remember { mutableStateOf("") }
    var invitationCode by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    var governorateExpanded by remember { mutableStateOf(false) }
    var areaExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getGovernorates()
    }

    LaunchedEffect(governorates) {
        val defaultGovernorate = governorates.firstOrNull()
        if (defaultGovernorate != null) {
            governorate = defaultGovernorate.name
            viewModel.getAreas(defaultGovernorate.id)
        }
    }

    LaunchedEffect(areas) {
            area = areas.firstOrNull()?.name ?: ""

    }



     LaunchedEffect(Unit) {
         viewModel.message.collect { message ->
             snackbarHostState.showSnackbar(message)
         }
     }

    val context = LocalContext.current

    val qrScannerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val contents = result.data?.getStringExtra("SCAN_RESULT")
            invitationCode = contents ?: ""
        }
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val intent = Intent(context, QRCodeScannerActivity::class.java)
            qrScannerLauncher.launch(intent)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.onPrimary)
                .padding(horizontal = 24.dp)
                .padding(
                    top = modifier.calculateTopPadding(),
                    bottom = modifier.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {
            OneIconCard(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                headerTxt = stringResource(R.string.register_new_account),
                onClick = {
                    onBackClick()
                },
                titleSize = 14
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
                onValueChange = {
                    governorate = it
                    viewModel.getAreas(governorates.find { it.name == governorate }?.id ?: 0)
                },
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

            CustomTextFieldWithIcon(
                value = invitationCode,
                onValueChange = { invitationCode = it },
                label = stringResource(R.string.invitation_code),
                placeholder = stringResource(R.string.enter_invitation_code),
                onIconClick = {
                    cameraPermissionLauncher.launch(Manifest.permission.CAMERA)

                },
                readOnly = true,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.signUp(
                        username = username,
                        pharmacyName = pharmacyName,
                        email = email,
                        phone = phone,
                        governorate = governorate,
                        areaId = areas.find { it.name == area }?.id ?: 0,
                        addressDetails = addressDetails,
                        invitationCode = invitationCode,
                        password = password,
                        confirmPassword = confirmPassword
                    )
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

        when(registerState){
            is Response.Error -> {
                NoInternet()
            }
            is Response.Loading -> {
                LoadingView()
            }
            is Response.Success -> {
                onRegisterClick()
            }
            null -> {
            }
        }
    }
}



