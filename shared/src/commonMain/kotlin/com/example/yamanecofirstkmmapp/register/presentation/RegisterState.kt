package com.example.yamanecofirstkmmapp.register.presentation

import com.example.yamanecofirstkmmapp.core.firebase.presentation.FirebaseException
import com.example.yamanecofirstkmmapp.core.firebase.presentation.User


data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: FirebaseException? = null,
    val user: User = User(),
    val isRegistered: Boolean = false
)