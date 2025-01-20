package com.galeria.medicationstracker

import android.app.*
import com.google.firebase.*
import dagger.hilt.android.*

@HiltAndroidApp
class TrackerApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}