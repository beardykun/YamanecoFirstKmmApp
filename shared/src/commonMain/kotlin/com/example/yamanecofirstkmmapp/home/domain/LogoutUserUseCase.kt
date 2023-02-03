package com.example.yamanecofirstkmmapp.home.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.util.Resource

class LogoutUserUseCase(private val firebaseAuthentication: FirebaseAuthentication) {

    suspend fun execute(): Resource<Boolean> {
        return firebaseAuthentication.logOut()
    }
}