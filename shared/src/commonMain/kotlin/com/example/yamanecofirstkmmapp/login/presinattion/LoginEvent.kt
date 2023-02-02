package com.example.yamanecofirstkmmapp.login.presinattion

sealed class LoginEvent {
    object Login : LoginEvent()
    object Register : LoginEvent()
    data class EditEmail(val newEmail: String) : LoginEvent()
    data class EditPassword(val newPassword: String) : LoginEvent()
    object OnErrorSeen : LoginEvent()
    object ForgotPassword : LoginEvent()
}