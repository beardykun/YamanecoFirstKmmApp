package com.example.yamanecofirstkmmapp.home.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication

class LogoutUserUseCase(private val firebaseAuthentication: FirebaseAuthentication) {

    suspend fun execute() {
        firebaseAuthentication.logOut()
    }
}