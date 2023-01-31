package com.example.yamanecofirstkmmapp.start.presentation

import com.example.yamanecofirstkmmapp.start.domain.StartAppUseCase
import com.example.yamanecofirstkmmapp.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StartViewModel(
    private val startAppUseCase: StartAppUseCase,
    private val coroutineScope: CoroutineScope?
) {

    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(StartState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000),
        StartState()
    ).toCommonStateFlow()

    fun redirectToCorrectScreen() = viewModelScope.launch {
        val user = startAppUseCase.execute()
        if (user == null) {
            _state.update {
                it.copy(
                    status = Status.NOT_LOGGED_IN
                )
            }
        } else {
            _state.update {
                it.copy(
                    status = Status.LOGGED_IN,
                    currentUser = user
                )
            }
        }
    }
}