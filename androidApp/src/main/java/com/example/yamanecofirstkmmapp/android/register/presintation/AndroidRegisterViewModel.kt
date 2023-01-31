package com.example.yamanecofirstkmmapp.android.register.presintation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yamanecofirstkmmapp.login.domain.LoginUserUseCase
import com.example.yamanecofirstkmmapp.register.domain.RegisterUserUseCase
import com.example.yamanecofirstkmmapp.register.presentation.RegisterEvent
import com.example.yamanecofirstkmmapp.register.presentation.RegisterViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidRegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val viewModel by lazy {
        RegisterViewModel(
            registerUserUseCase = registerUserUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: RegisterEvent) {
        viewModel.onEvent(event)
    }
}