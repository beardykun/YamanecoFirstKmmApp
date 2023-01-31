package com.example.yamanecofirstkmmapp.start.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import dev.gitlive.firebase.auth.FirebaseUser

class StartAppUseCase(
    private val firebaseAuthentication: FirebaseAuthentication
) {
    suspend fun execute(): FirebaseUser? {
        return firebaseAuthentication.checkCurrentUser()
    }
}