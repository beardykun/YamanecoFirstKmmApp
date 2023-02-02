package com.example.yamanecofirstkmmapp.core.firebase.domain

import com.example.yamanecofirstkmmapp.util.Resource
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseUser

interface IFirebaseAuthentication {

    suspend fun createNewUser(email: String, password: String): Resource<String>

    suspend fun loginWithEmailAndPass(email: String, password: String): Resource<AuthResult>

    suspend fun checkCurrentUser(): FirebaseUser?

    suspend fun logOut()

    suspend fun resetPassword(email: String)
}