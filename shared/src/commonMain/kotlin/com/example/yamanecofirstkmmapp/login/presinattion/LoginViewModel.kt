package com.example.yamanecofirstkmmapp.login.presinattion

import com.example.yamanecofirstkmmapp.core.presentation.FirebaseException
import com.example.yamanecofirstkmmapp.login.domain.LoginUserUseCase
import com.example.yamanecofirstkmmapp.util.Resource
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val registerUserUseCase: LoginUserUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(LoginState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        LoginState()
    ).toCommonStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EditEmail -> {
                _state.update {
                    it.copy(
                        email = event.newEmail
                    )
                }
            }
            is LoginEvent.EditPassword -> {
                _state.update {
                    it.copy(
                        password = event.newPassword
                    )
                }
            }
            is LoginEvent.OnErrorSeen -> {
                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }
            is LoginEvent.Login -> {
                loginUser(state.value)
            }
        }
    }


    private fun loginUser(state: LoginState) {
        if (state.isLoading || state.email.isBlank() || state.password.isBlank()) {
            _state.update {
                it.copy(
                    error = FirebaseException(Exception("email or password is empty"))
                )
            }
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
                password = state.password
            )

            when (result) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            userId = result.data?.user?.uid ?: ""
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