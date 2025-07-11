package com.example.atonce.presentation.forgotpassword.view

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.domain.entity.ForgotPasswordState
import com.example.atonce.presentation.forgotpassword.view.component.ConfirmResetScreen
import com.example.atonce.presentation.forgotpassword.view.component.EmailScreen
import com.example.atonce.presentation.forgotpassword.view.component.NewPasswordScreen
import com.example.atonce.presentation.forgotpassword.view.component.OtpScreen
import com.example.atonce.presentation.forgotpassword.view.component.SuccessResetScreen
import com.example.atonce.presentation.forgotpassword.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    snackbarHostState: SnackbarHostState,
    onBackClick: () -> Unit,
    onContinueClick: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.message.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    when (val state = uiState.value) {
        is ForgotPasswordState.EnterEmail -> {
            EmailScreen(
                isLoading = state.isLoading,
                onBackClick = onBackClick,
                onSubmitClick = { email ->
                    viewModel.submitEmail(email)
                }
            )
        }
        is ForgotPasswordState.EnterOtp -> {
            OtpScreen(
                isLoading = state.isLoading,
                email = state.email,
                onBackClick = onBackClick,
                onSubmitClick = { otp ->
                    viewModel.submitOtp(state.email, otp)
                }
            )
        }
        is ForgotPasswordState.SetNewPassword -> {
            NewPasswordScreen(
                isLoading = state.isLoading,
                onBackClick = onBackClick,
                onSubmitClick = { newPassword, confirmPassword ->
                    viewModel.submitNewPassword(state.email, newPassword, confirmPassword)
                }
            )
        }
        is ForgotPasswordState.ResetSuccess -> {
            SuccessResetScreen(
                isLoading = state.isLoading,
                onContinueClick = {
                    viewModel.success()
                    onContinueClick()
                }
            )
        }
    }

}