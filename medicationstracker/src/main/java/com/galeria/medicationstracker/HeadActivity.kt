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
            BottomNavBar(items, navController, headViewModel)
          },
          content = {
            MedTrackerNavGraph(
              modifier = Modifier.padding(it),
              navController = navController
            )
            
          }
        )
        // State for managing snackbar messages
        /*         val snackbarHostState = remember { SnackbarHostState() }
                SnackbarHandler(snackbarHostState)
                
                Scaffold(
                  snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                  modifier = Modifier,
                  containerColor = MedTrackerTheme.colors.secondaryBackground,
                ) { innerPadding ->
                  
                  // Initialize navigation controller
                  val navController = rememberNavController()
                  // Get the current context for navigation
                  val context: Context = LocalContext.current
                  
                  // Define the navigation graph.
                  NavHost(
                    navController = navController,
                    startDestination = Routes.Login,
                    modifier = Modifier
                      .fillMaxSize()
                      .padding(innerPadding)
                      .windowInsetsPadding(WindowInsets.safeGestures)
                  
                  ) {
                    // Home route with login, signup, and password reset actions
                    composable<Routes.Login> {
                      LoginScreen(
                        onLoginClick = { userType ->
                          when (userType) {
                            UserType.ADMIN -> {
                              val intent = Intent(context, AdminActivity::class.java)
                              startActivity(intent)
                            }
                            
                            UserType.DOCTOR -> {
                              val intent = Intent(context, DoctorActivity::class.java)
                              startActivity(intent)
                            }
                            
                            else -> {
                              val intent =
                                Intent(context, ApplicationActivity::class.java)
                              startActivity(intent)
                            }
                          }
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
                        navigateHome = { navController.navigate(Routes.Login) }
                      )
                    }
                    
                    // Password recovery screen route
                    composable<Routes.PasswordRecovery> { backStackEntry ->
                      // Retrieve and pass the email argument
                      val args = backStackEntry.toRoute<Routes.PasswordRecovery>()
                      AccountRecoveryScreen(
                        passedEmail = args.email ?: "",
                        navigateHome = { navController.navigate(Routes.Login) },
                      )
                    }
                  }
                } */
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