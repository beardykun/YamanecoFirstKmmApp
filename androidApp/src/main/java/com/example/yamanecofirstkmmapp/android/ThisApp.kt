package com.example.yamanecofirstkmmapp.android

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ThisApp : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        //FirebaseApp.initializeApp(this)
    }
}