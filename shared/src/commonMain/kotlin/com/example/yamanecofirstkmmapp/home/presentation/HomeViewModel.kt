package com.example.yamanecofirstkmmapp.home.presentation

import com.example.yamanecofirstkmmapp.home.domain.GetUserUseCase
import com.example.yamanecofirstkmmapp.home.domain.LogoutUserUseCase
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HomeState())
    val state = combine(_state, getUserUseCase.execute()) { state, user ->
        if (user != state.user) {
            if (user == null)
                state.copy(isLogOut = true)
            else
                state.copy(user = user)
        } else state
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        HomeState()
    ).toCommonStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LogOut -> {
                _state.update {
                    it.copy(
                        isLogOut = true
                    )
                }
                logOut()
            }
        }
    }

    private fun logOut() = viewModelScope.launch {
        logoutUserUseCase.execute()
    }
}
