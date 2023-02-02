package com.example.yamanecofirstkmmapp.login.presinattion

import com.example.yamanecofirstkmmapp.core.presentation.FirebaseException

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val userId: String? = null,
    val error: FirebaseException? = null,
    val newRegistration: Boolean = false,
    val navigateToResetPassword: Boolean = false
)