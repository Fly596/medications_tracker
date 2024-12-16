package com.galeria.medicationstracker.ui.admin

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.model.navigation.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*

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
              startDestination = Routes.AdminDashboard,
              modifier =
                Modifier
                  .fillMaxSize()
                  .padding(it)/* .padding(horizontal = 16.dp) */
            
            ) {
              composable<Routes.AdminDashboard> {
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