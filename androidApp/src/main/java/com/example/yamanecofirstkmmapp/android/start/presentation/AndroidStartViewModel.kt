package com.example.yamanecofirstkmmapp.android.start.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.yamanecofirstkmmapp.start.domain.StartAppUseCase
import com.example.yamanecofirstkmmapp.start.presentation.StartViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidStartViewModel @Inject constructor(
    private val startAppUseCase: StartAppUseCase
) : ViewModel(
) {

    val viewModel by lazy {
        StartViewModel(
            startAppUseCase = startAppUseCase,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

}