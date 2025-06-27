package com.example.atonce.presentation.forgotpassword.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.domain.entity.ForgotPasswordState
import com.example.atonce.presentation.forgotpassword.view.component.ConfirmResetScreen
import com.example.atonce.presentation.forgotpassword.view.component.EmailScreen
import com.example.atonce.presentation.forgotpassword.view.component.OtpScreen
import com.example.atonce.presentation.forgotpassword.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

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
        is ForgotPasswordState.ConfirmReset -> {
            ConfirmResetScreen(

            )
        }
        is ForgotPasswordState.SetNewPassword -> {
        }
        is ForgotPasswordState.ResetSuccess -> {
        }
    }

}