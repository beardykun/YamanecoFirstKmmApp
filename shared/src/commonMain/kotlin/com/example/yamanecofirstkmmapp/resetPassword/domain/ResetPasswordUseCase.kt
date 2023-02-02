package com.example.yamanecofirstkmmapp.resetPassword.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication

class ResetPasswordUseCase(
    private val firebaseAuthentication: FirebaseAuthentication
) {

    suspend fun evoke(email: String) {
        firebaseAuthentication.resetPassword(email)
    }
}