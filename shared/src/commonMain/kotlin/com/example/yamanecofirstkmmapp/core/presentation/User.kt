package com.example.yamanecofirstkmmapp.core.presentation

import dev.gitlive.firebase.firestore.FieldValue

@kotlinx.serialization.Serializable
data class User(
    val id: String = "",
    val name: String = "",
    val createdAt: Double = FieldValue.serverTimestamp
)
