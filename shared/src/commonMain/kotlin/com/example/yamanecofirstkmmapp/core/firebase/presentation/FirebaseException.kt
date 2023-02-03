package com.example.yamanecofirstkmmapp.core.firebase.presentation

class FirebaseException(val error: Throwable) : Exception(error.message)