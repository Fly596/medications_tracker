package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.galeria.medicationstracker.ui.screens.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.login.LoginScreenViewModel
import com.galeria.medicationstracker.ui.theme.SpeechRecognitionAppTheme

class HeadActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: LoginScreenViewModel = LoginScreenViewModel()

        enableEdgeToEdge()
        setContent {
            SpeechRecognitionAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(24.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeechRecognitionAppTheme {
        //Greeting("Android")
    }
}