package com.example.yamanecofirstkmmapp.resetPassword.presentation

import com.example.yamanecofirstkmmapp.login.domain.LoginUserUseCase
import com.example.yamanecofirstkmmapp.resetPassword.domain.ResetPasswordUseCase
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ResetPasswordViewModel(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        ResetPasswordState()
    ).toCommonStateFlow()

    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.EditEmail -> {
                _state.update {
                    it.copy(
                        email = event.newEmail
                    )
                }
            }
            ResetPasswordEvent.NavigateToHome -> {
                _state.update {
                    it.copy(
                        navigateToHome = true
                    )
                }
            }
            ResetPasswordEvent.OnResetPasswordClick -> {
                _state.update {
                    it.copy(
                        onResetClicked = true
                    )
                }
            }
        }
    }
}