package com.example.yamanecofirstkmmapp.resetPassword.presentation

import com.example.yamanecofirstkmmapp.core.firebase.presentation.FirebaseException

data class ResetPasswordState(
    val email: String = "",
    val navigateToHome: Boolean = false,
    val error: FirebaseException? = null,
)