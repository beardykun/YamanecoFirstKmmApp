package com.example.yamanecofirstkmmapp.core.presentation

class FirebaseException(val error: Throwable) : Exception(
    "An error has occurred : ${error.message}"
)