package com.example.yamanecofirstkmmapp.core.firebase.domain

import com.example.yamanecofirstkmmapp.core.presentation.User

interface IFirebaseFireStore {

    suspend fun getNewDocumentId(): String

    suspend fun addNewDocumentToCollection(user: User)

    suspend fun getUser(id: String): User
}