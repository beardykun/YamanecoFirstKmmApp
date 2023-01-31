package com.example.yamanecofirstkmmapp.start.presentation

import dev.gitlive.firebase.auth.FirebaseUser

data class StartState(
    val currentUser: FirebaseUser? = null,
    val status: Status = Status.NONE
)

enum class Status {
    NOT_LOGGED_IN,
    LOGGED_IN,
    NONE
}