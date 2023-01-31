package com.example.yamanecofirstkmmapp.login.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.util.Resource
import dev.gitlive.firebase.auth.AuthResult

class LoginUserUseCase(
    private val firebaseAuthentication: FirebaseAuthentication
) {
    suspend fun execute(email: String, password: String): Resource<AuthResult> {
        return try {
            firebaseAuthentication.loginWithEmailAndPass(email, password)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}