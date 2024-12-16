package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.admin.DBDataScreen
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

class AdminActivity : ComponentActivity() {

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      MedTrackerTheme {
        val navController = rememberNavController()


        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.displayCutout),
          topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            // if (shouldDisplayTopBar(currentDestination)) {
            val title = when (currentDestination) {
              "com.galeria.medicationstracker.model.navigation.Routes.Dashboard" -> "Today, "
              "com.galeria.medicationstracker.model.navigation.Routes.Medications" -> "My Meds"
              "com.galeria.medicationstracker.model.navigation.Routes.Profile" -> "My Profile"
              "com.galeria.medicationstracker.model.navigation.Routes.NewMedication" -> "Add medication"
              "com.galeria.medicationstracker.model.navigation.Routes.AppSettings" -> "App Settings"
              "com.galeria.medicationstracker.model.navigation.Routes.NotificationsSettings" -> "Notifications Settings"
              else -> null
            }

            FlyTopAppBar(
              title = title.toString()
            )

          },
          containerColor = MedTrackerTheme.colors.secondaryBackground,
          content = {
            NavHost(
              navController = navController,
              startDestination = Routes.DataBaseData,
              modifier =
                Modifier
                  .fillMaxSize()
                  .padding(it)/* .padding(horizontal = 16.dp) */

            ) {
              composable<Routes.DataBaseData> {
                DBDataScreen(
                  modifier = Modifier,
                  onProfileClick = {
                    navController.navigate(Routes.Profile)
                  }
                )
              }
            }
          }
        )
      }
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  MedTrackerTheme {
    // Greeting("Android")
  }
}