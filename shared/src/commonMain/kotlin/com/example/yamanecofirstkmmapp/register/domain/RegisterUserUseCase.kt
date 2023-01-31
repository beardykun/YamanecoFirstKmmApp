package com.example.yamanecofirstkmmapp.register.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseFireStore
import com.example.yamanecofirstkmmapp.core.presentation.User
import com.example.yamanecofirstkmmapp.util.Resource

class RegisterUserUseCase(
    private val firebaseAuthentication: FirebaseAuthentication,
    private val fireStore: FirebaseFireStore
) {
    suspend fun execute(email: String, password: String, user: User): Resource<String> {
        val res = firebaseAuthentication.createNewUser(email, password)
        if (res.data != null) {
            val us = user.copy(id = res.data)
            fireStore.addNewDocumentToCollection(us)
        }
        return res
    }
}