package com.galeria.medicationstracker

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.model.*
import com.galeria.medicationstracker.model.navigation.Routes.NavigationRoutes
import com.galeria.medicationstracker.ui.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*
import com.google.firebase.*
import kotlinx.coroutines.*
import java.time.*

class HeadActivity : ComponentActivity() {
  
  private val headViewModel: HeadViewModel by viewModels()
  
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    // Initialize Firebase.
    FirebaseApp.initializeApp(this)
    
    setContent {
      val navController = rememberNavController()
      
      MedTrackerTheme {
        
        val items = bottomNavItems()
        
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.displayCutout),
          containerColor = MedTrackerTheme.colors.secondaryBackground,
          
          topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            
            val routeTitles = mapOf(
              NavigationRoutes.USER_DASHBOARD to {
                "Today, ${
                  getStringFormattedDate(
                    LocalDate.now()
                  )
                }"
              },
              NavigationRoutes.MEDICATIONS to { "My Meds" },
              NavigationRoutes.PROFILE to { "My Profile" },
              NavigationRoutes.NEW_MEDICATION to { "Add medication" },
              NavigationRoutes.APP_SETTINGS to { "App Settings" },
              NavigationRoutes.NOTIFICATIONS_SETTINGS to { "Notifications Settings" },
              NavigationRoutes.DOC_DASHBOARD to { "Welcome, Doctor" },
              NavigationRoutes.ADMIN_DASHBOARD to { "Hello, Admin" },
            )
            
            val routesWithoutTopBar = listOf(
              NavigationRoutes.LOGIN,
              NavigationRoutes.REGISTRATION,
              NavigationRoutes.PASSWORD_RECOVERY
            )
            val title = routeTitles[currentDestination]?.invoke()
            
            if (title != null && currentDestination !in routesWithoutTopBar) {
              FlyTopAppBar(title = title)
            }
            
          },
          bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            val routesWithoutBottomBar = listOf(
              NavigationRoutes.LOGIN,
              NavigationRoutes.REGISTRATION,
              NavigationRoutes.PASSWORD_RECOVERY
            )
            
            if (currentDestination !in routesWithoutBottomBar) {
              BottomNavBar(items, navController, headViewModel)
              
            }
            
          },
          content = {
            MedTrackerNavGraph(
              modifier = Modifier.padding(it),
              navController = navController
            )
            
          }
        )
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