package com.galeria.medicationstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.autentification.create_account.SignupScreen
import com.galeria.medicationstracker.ui.screens.autentification.create_account.SignupScreenViewModel
import com.galeria.medicationstracker.ui.screens.autentification.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.autentification.login.LoginScreenViewModel
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class HeadActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    FirebaseApp.initializeApp(this)

    enableEdgeToEdge()

    setContent {
      MedicationsTrackerAppTheme {
        val navController = rememberNavController()
        val context: Context = LocalContext.current

        // TODO: remember current user.
        /*        var startDestination by remember { mutableStateOf("Routes.Home") }

                Firebase.auth.addAuthStateListener { firebaseAuth ->
                  val user = firebaseAuth.currentUser
                  if (user != null) {
                    startDestination = "mainScreen"
                  } else {
                    startDestination = "loginScreen"
                  }
                }*/

        Scaffold(
        ) { innerPadding ->
          NavHost(
            navController = navController,
            startDestination = Routes.Home,
            modifier = Modifier.padding(innerPadding)
          ) {

            // region Autehntication
            composable<Routes.Home> {
              LoginScreen(
                onLoginClick = {
                  val intent = Intent(context, ApplicationActivity::class.java)
                  startActivity(intent)
                },
                onSignupClick = {
                  navController.navigate(
                    Routes.Registration
                  )
                },
                modifier = Modifier
                  .fillMaxSize()
                  .padding(24.dp)
              )
            }

            composable<Routes.Registration> {
              SignupScreen(
                onCreateAccountClick = {
                  navController.navigate(
                    Routes.Home
                  )
                },
                modifier = Modifier
                  .fillMaxSize()
                  .padding(24.dp)
              )
            }
            // endregion
          }
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  MedicationsTrackerAppTheme {
    // Greeting("Android")
  }
}