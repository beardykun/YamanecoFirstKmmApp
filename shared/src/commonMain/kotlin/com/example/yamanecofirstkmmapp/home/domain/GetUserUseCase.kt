package com.example.yamanecofirstkmmapp.home.domain

import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseAuthentication
import com.example.yamanecofirstkmmapp.core.firebase.data.FirebaseFireStore
import com.example.yamanecofirstkmmapp.core.presentation.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserUseCase(
    private val fireStore: FirebaseFireStore,
    private val firebaseAuthentication: FirebaseAuthentication
) {

    fun execute(): Flow<User?> = flow {
        val userId = firebaseAuthentication.checkCurrentUser()?.uid
        userId?.let {
            emit(fireStore.getUser(it))
        } ?: kotlin.run {
            emit(null)
        }
    }
}