package com.example.yamanecofirstkmmapp.home.presentation

import com.example.yamanecofirstkmmapp.core.firebase.presentation.FirebaseException
import com.example.yamanecofirstkmmapp.core.firebase.presentation.User

data class HomeState(
    val user: User = User(),
    val isLogOut: Boolean = false,
    val error: FirebaseException? = null
)