package com.example.yamanecofirstkmmapp.resetPassword.presentation

data class ResetPasswordState(
    val email: String = "",
    val onResetClicked: Boolean = false,
    val navigateToHome: Boolean = false
)