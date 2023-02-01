package com.example.yamanecofirstkmmapp.android

import android.app.Application
import com.example.yamanecofirstkmmapp.shared.localization.localizationContext
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThisApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        localizationContext = this
    }
}