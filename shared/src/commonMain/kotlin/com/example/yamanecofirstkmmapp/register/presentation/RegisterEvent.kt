package com.example.yamanecofirstkmmapp.register.presentation

sealed class RegisterEvent {
    object Register : RegisterEvent()
    data class EditEmail(val newEmail: String) : RegisterEvent()
    data class EditPassword(val newPassword: String) : RegisterEvent()
    data class EditName(val newName: String) : RegisterEvent()
    object OnErrorSeen : RegisterEvent()
}