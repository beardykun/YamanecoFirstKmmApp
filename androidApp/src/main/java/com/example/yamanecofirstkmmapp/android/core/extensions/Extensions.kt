package com.example.yamanecofirstkmmapp.android.core.extensions

import androidx.navigation.NavController

fun NavController.navigateWithDestroy(routes: String) {
    this.popBackStack()
    this.navigate(routes)
}