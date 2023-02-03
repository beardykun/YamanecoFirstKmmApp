package com.example.yamanecofirstkmmapp.resetPassword.presentation

import com.example.yamanecofirstkmmapp.core.presentation.FirebaseException
import com.example.yamanecofirstkmmapp.resetPassword.domain.ResetPasswordUseCase
import com.example.yamanecofirstkmmapp.util.Resource
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                        email = event.email
                    )
                }
            }
            ResetPasswordEvent.OnResetPasswordClick -> {
                //TODO VALIDATE PASSWORD
                resetPassword(_state.value)
            }
            ResetPasswordEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
        }
    }

    private fun resetPassword(state: ResetPasswordState) = viewModelScope.launch {
        when (val result = resetPasswordUseCase.evoke(state.email)) {
            is Resource.Success -> {
                _state.update {
                    it.copy(
                        navigateToHome = true
                    )
                }
            }
            is Resource.Error -> {
                _state.update {
                    it.copy(
                        error = FirebaseException(result.throwable!!)
                    )
                }
            }
        }
    }
}