package com.example.yamanecofirstkmmapp.core.firebase.data

import com.example.yamanecofirstkmmapp.core.firebase.domain.IFirebaseFireStore
import com.example.yamanecofirstkmmapp.core.firebase.presentation.User
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore

const val USERS_COLLECTION = "users"

class FirebaseFireStore : IFirebaseFireStore {

    private var fireStore: FirebaseFirestore? = null

    private fun getInstance(): FirebaseFirestore {
        if (fireStore == null) {
            fireStore = Firebase.firestore
        }
        return fireStore!!
    }

    override suspend fun getNewDocumentId(): String {
        return getInstance().collection(USERS_COLLECTION).document.id
    }

    override suspend fun addNewDocumentToCollection(user: User) {
        getInstance().collection(USERS_COLLECTION).document(user.id).set(user)
    }

    override suspend fun getUser(id: String): User {
        val userRef = getInstance().collection(USERS_COLLECTION).document(id).get()
        return userRef.data()
    }
}