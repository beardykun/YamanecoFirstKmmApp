package com.example.yamanecofirstkmmapp.resetPassword.presentation

sealed class ResetPasswordEvent {
    data class EditEmail(val email: String) : ResetPasswordEvent()
    object OnResetPasswordClick : ResetPasswordEvent()
    object OnErrorSeen : ResetPasswordEvent()
}