package com.example.yamanecofirstkmmapp.android.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yamanecofirstkmmapp.login.domain.LoginUserUseCase
import com.example.yamanecofirstkmmapp.login.presinattion.LoginEvent
import com.example.yamanecofirstkmmapp.login.presinattion.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidLoginViewModel @Inject constructor(
    private val registerUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val viewModel by lazy {
        LoginViewModel(
            registerUserUseCase = registerUserUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: LoginEvent) {
        viewModel.onEvent(event)
    }
}