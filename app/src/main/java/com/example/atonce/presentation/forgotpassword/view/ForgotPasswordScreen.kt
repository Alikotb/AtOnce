package com.example.atonce.presentation.forgotpassword.view

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.atonce.domain.entity.ForgotPasswordState
import com.example.atonce.presentation.forgotpassword.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = koinViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState.value) {
        is ForgotPasswordState.EnterEmail -> {
        }
        is ForgotPasswordState.EnterOtp -> {
        }
        is ForgotPasswordState.ConfirmReset -> {
        }
        is ForgotPasswordState.SetNewPassword -> {
        }
        is ForgotPasswordState.ResetSuccess -> {
        }
    }

}