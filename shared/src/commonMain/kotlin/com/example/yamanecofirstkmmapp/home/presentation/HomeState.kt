package com.example.yamanecofirstkmmapp.home.presentation

import com.example.yamanecofirstkmmapp.core.presentation.User

data class HomeState(
    val user: User = User(),
    val isLogOut: Boolean = false
)