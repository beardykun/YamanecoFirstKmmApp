package com.example.yamanecofirstkmmapp.resetPassword.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.util.Resource

class ResetPasswordUseCase(
    private val firebaseAuthentication: FirebaseAuthentication
) {

    suspend fun evoke(email: String): Resource<Boolean> {
        return firebaseAuthentication.resetPassword(email)
    }
}