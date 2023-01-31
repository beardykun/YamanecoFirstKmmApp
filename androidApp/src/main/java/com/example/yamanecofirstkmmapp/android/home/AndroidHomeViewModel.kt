package com.example.yamanecofirstkmmapp.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yamanecofirstkmmapp.home.domain.GetUserUseCase
import com.example.yamanecofirstkmmapp.home.domain.LogoutUserUseCase
import com.example.yamanecofirstkmmapp.home.presentation.HomeEvent
import com.example.yamanecofirstkmmapp.home.presentation.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidHomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    private val viewModel by lazy {
        HomeViewModel(
            getUserUseCase = getUserUseCase,
            logoutUserUseCase = logoutUserUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: HomeEvent) {
        viewModel.onEvent(event)
    }
}