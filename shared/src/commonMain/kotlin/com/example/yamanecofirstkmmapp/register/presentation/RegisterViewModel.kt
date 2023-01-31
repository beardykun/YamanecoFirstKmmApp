package com.example.yamanecofirstkmmapp.register.presentation

import com.example.yamanecofirstkmmapp.core.presentation.FirebaseException
import com.example.yamanecofirstkmmapp.core.presentation.User
import com.example.yamanecofirstkmmapp.register.domain.RegisterUserUseCase
import com.example.yamanecofirstkmmapp.util.Resource
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        RegisterState()
    ).toCommonStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EditName -> {
                _state.update {
                    it.copy(
                        name = event.newName
                    )
                }
            }
            is RegisterEvent.EditEmail -> {
                _state.update {
                    it.copy(
                        email = event.newEmail
                    )
                }
            }
            is RegisterEvent.EditPassword -> {
                _state.update {
                    it.copy(
                        password = event.newPassword
                    )
                }
            }
            is RegisterEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
            is RegisterEvent.Register -> {
                registerUser(state.value)
            }
        }
    }


    private fun registerUser(state: RegisterState) {
        if (state.isLoading || state.email.isBlank() || state.password.isBlank()) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            val result = registerUserUseCase.execute(
                email = state.email,
                password = state.password,
                user = User(name = state.name)
            )

            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRegistered = true
                        )
                    }
                }
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = FirebaseException(result.throwable!!)
                        )
                    }
                }
            }
        }
    }
}