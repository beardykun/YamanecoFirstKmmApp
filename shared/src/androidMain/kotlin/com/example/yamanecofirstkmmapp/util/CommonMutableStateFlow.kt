package com.example.yamanecofirstkmmapp.util

import kotlinx.coroutines.flow.MutableStateFlow

actual class CommonMutableStateFlow<T> actual constructor(private val stateFlow: MutableStateFlow<T>) :
    MutableStateFlow<T> by stateFlow