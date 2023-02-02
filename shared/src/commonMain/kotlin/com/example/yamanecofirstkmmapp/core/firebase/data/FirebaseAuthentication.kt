package com.example.yamanecofirstkmmapp.core.firebase.data

import com.example.yamanecofirstkmmapp.core.firebase.domain.IFirebaseAuthentication
import com.example.yamanecofirstkmmapp.util.Resource
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class FirebaseAuthentication : IFirebaseAuthentication {

    private var firebaseAuth: FirebaseAuth? = null

    private fun getInstance(): FirebaseAuth {
        if (firebaseAuth == null) {
            firebaseAuth = Firebase.auth
        }
        return firebaseAuth!!
    }

    override suspend fun createNewUser(email: String, password: String): Resource<String> {
        return try {
            val response = getInstance().createUserWithEmailAndPassword(email, password)
            if (response.user == null) {
                return Resource.Error(Exception("TAGGER creation failed"))
            }
            Resource.Success(response.user!!.uid)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun loginWithEmailAndPass(
        email: String,
        password: String
    ): Resource<AuthResult> {
        return try {
            val authResult = getInstance().signInWithEmailAndPassword(email, password)
            if (authResult.user == null) {
                return Resource.Error(Exception("TAGGER login failed"))
            }
            Resource.Success(authResult)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun checkCurrentUser(): FirebaseUser? {
        val user = getInstance().currentUser
        return if (user == null) {
            logOut()
            null
        } else user
    }

    override suspend fun logOut() {
        getInstance().signOut()
    }

    override suspend fun resetPassword(email: String) {
        getInstance().sendPasswordResetEmail(email)
    }
}
