package com.galeria.medicationstracker

import android.app.Application
import com.google.firebase.FirebaseApp

class TrackerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}