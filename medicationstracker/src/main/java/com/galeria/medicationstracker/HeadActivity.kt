package com.galeria.medicationstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.auth.accountrecovery.AccountRecoveryScreen
import com.galeria.medicationstracker.ui.screens.auth.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.auth.signup.SignupScreen
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch

class HeadActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Initialize Firebase.
    FirebaseApp.initializeApp(this)

    setContent {
      MedTrackerTheme {

        // State for managing snackbar messages
        val snackbarHostState = remember { SnackbarHostState() }
        SnackbarHandler(snackbarHostState)

        Scaffold(
          snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
          modifier = Modifier,
          containerColor = MedTrackerTheme.colors.primaryBackground,
        ) { innerPadding ->
          // Initialize navigation controller
          val navController = rememberNavController()
          // Get the current context for navigation
          val context: Context = LocalContext.current

          // Define the navigation graph.
          NavHost(
            navController = navController,
            startDestination = Routes.Home,
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
              .windowInsetsPadding(WindowInsets.safeContent)

          ) {
            // Home route with login, signup, and password reset actions
            composable<Routes.Home> {
              LoginScreen(
                onLoginClick = {
                  // Navigate to main application screen
                  val intent = Intent(context, ApplicationActivity::class.java)
                  startActivity(intent)
                },
                onSignupClick = { email ->
                  // Navigate to the registration screen with email
                  navController.navigate(Routes.Registration(email = email))
                },
                onResetPasswordClick = { email ->
                  // Navigate to the password recovery screen with email
                  navController.navigate(Routes.PasswordRecovery(email = email))
                }
              )
            }

            // Registration screen route
            composable<Routes.Registration> { backStackEntry ->
              // Retrieve and pass the email argument.
              val args = backStackEntry.toRoute<Routes.Registration>()
              SignupScreen(
                passedEmail = args.email ?: "",
                navigateHome = { navController.navigate(Routes.Home) }
              )
            }

            // Password recovery screen route
            composable<Routes.PasswordRecovery> { backStackEntry ->
              // Retrieve and pass the email argument
              val args = backStackEntry.toRoute<Routes.PasswordRecovery>()
              AccountRecoveryScreen(
                passedEmail = args.email ?: "",
                navigateHome = { navController.navigate(Routes.Home) },
              )
            }
          }
        }
      }
    }
  }
}

@Composable
fun SnackbarHandler(snackbarHostState: SnackbarHostState) {
  val scope = rememberCoroutineScope()
  ObserveAsEvents(
    flow = SnackbarController.events,
    snackbarHostState
  ) { event ->
    scope.launch {
      snackbarHostState.currentSnackbarData?.dismiss()

      val result =
        snackbarHostState.showSnackbar(
          message = event.message,
          actionLabel = event.action?.name,
          duration = SnackbarDuration.Short,
        )

      if (result == SnackbarResult.ActionPerformed) {
        event.action?.action?.invoke()
      }
    }
  }
}
