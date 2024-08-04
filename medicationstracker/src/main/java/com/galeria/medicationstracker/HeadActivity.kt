package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.create_account.SignupScreen
import com.galeria.medicationstracker.ui.screens.create_account.SignupScreenViewModel
import com.galeria.medicationstracker.ui.screens.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.login.LoginScreenViewModel
import com.galeria.medicationstracker.ui.screens.start.StartScreen
import com.galeria.medicationstracker.ui.theme.SpeechRecognitionAppTheme
import com.google.firebase.FirebaseApp

class HeadActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        val viewModel = LoginScreenViewModel()
        val registerViewModel = SignupScreenViewModel()

        enableEdgeToEdge()
        setContent {
            SpeechRecognitionAppTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomAppBar() {} }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Routes.Home> {
                            LoginScreen(
                                onLoginClick = {
                                    navController.navigate(
                                        Routes.Summary
                                    )
                                },
                                onSignupClick = {
                                    navController.navigate(
                                        Routes.Registration
                                    )
                                },
                                viewModel = viewModel,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp)
                            )
                        }
                        composable<Routes.Summary> { backStackEntry ->
                            // val args = backStackEntry.toRoute<Routes.Summary>()
                            StartScreen(
                                // args.userEmail,
                                onNavigationSomewhere = {
                                    navController.navigate(
                                        Routes.Home
                                    )
                                }
                            )
                        }
                        composable<Routes.Registration> {
                            SignupScreen(
                                onCreateAccountClick = {
                                    navController.navigate(
                                        Routes.Home
                                    )
                                },
                                viewModel = registerViewModel,
                                viewModelLogin = viewModel,
                            )
                        }

                    }
                }


                /*                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                                   LoginScreen(
                                       navController,
                                       viewModel,
                                       modifier = Modifier
                                           .fillMaxSize()
                                           .padding(innerPadding)
                                           .padding(24.dp)
                                   )
                               } */
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeechRecognitionAppTheme {
        // Greeting("Android")
    }
}