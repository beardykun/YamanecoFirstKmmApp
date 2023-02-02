package com.example.yamanecofirstkmmapp.android.resetPassword.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yamanecofirstkmmapp.resetPassword.domain.ResetPasswordUseCase
import com.example.yamanecofirstkmmapp.resetPassword.presentation.ResetPasswordEvent
import com.example.yamanecofirstkmmapp.resetPassword.presentation.ResetPasswordViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val viewModel by lazy {
        ResetPasswordViewModel(
            resetPasswordUseCase = resetPasswordUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: ResetPasswordEvent) {
        viewModel.onEvent(event)
    }
}