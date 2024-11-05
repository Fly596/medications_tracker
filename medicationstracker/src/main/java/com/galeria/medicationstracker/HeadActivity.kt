package com.galeria.medicationstracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.autentification.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.autentification.signup.SignupScreen
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.google.firebase.FirebaseApp
import kotlinx.coroutines.launch

class HeadActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    FirebaseApp.initializeApp(this)

    enableEdgeToEdge()

    setContent {
      MedTrackerTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
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

        Scaffold(
          snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
          modifier = Modifier.fillMaxSize(),
          containerColor = MedTrackerTheme.colors.primaryBackground,
        ) { innerPadding ->
          val navController = rememberNavController()
          val context: Context = LocalContext.current

          NavHost(
            navController = navController,
            startDestination = Routes.Home,
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
          ) {
            composable<Routes.Home> {
              LoginScreen(
                onLoginClick = {
                  val intent = Intent(context, ApplicationActivity::class.java)
                  startActivity(intent)
                },
                onSignupClick = { navController.navigate(Routes.Registration) },
              )
            }

            composable<Routes.Registration> {
              SignupScreen(navigateHome = { navController.navigate(Routes.Home) })
            }
          }
        }
      }
    }
  }
}
