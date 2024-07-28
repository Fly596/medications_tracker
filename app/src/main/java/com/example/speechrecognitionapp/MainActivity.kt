package com.example.speechrecognitionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.speechrecognitionapp.ui.screens.login.LoginScreen
import com.example.speechrecognitionapp.ui.screens.login.LoginViewModel
import com.example.speechrecognitionapp.ui.theme.SpeechRecognitionAppTheme
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        val viewModel = LoginViewModel()

        setContent {
            SpeechRecognitionAppTheme {
                LoginScreen(
                    viewModel,
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                )
            }
        }
    }

}