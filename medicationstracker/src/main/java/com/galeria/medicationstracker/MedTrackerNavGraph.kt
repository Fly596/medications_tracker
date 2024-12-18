package com.galeria.medicationstracker

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.data.*
import com.galeria.medicationstracker.model.navigation.*
import com.galeria.medicationstracker.ui.admin.*
import com.galeria.medicationstracker.ui.doctor.*
import com.galeria.medicationstracker.ui.screens.auth.accountrecovery.*
import com.galeria.medicationstracker.ui.screens.auth.login.*
import com.galeria.medicationstracker.ui.screens.auth.signup.*
import com.galeria.medicationstracker.ui.screens.dashboard.*
import com.galeria.medicationstracker.ui.screens.medications.*
import com.galeria.medicationstracker.ui.screens.medications.logs.*
import com.galeria.medicationstracker.ui.screens.medications.update.*
import com.galeria.medicationstracker.ui.screens.profile.*
import com.galeria.medicationstracker.ui.screens.profile.notifications.*
import com.galeria.medicationstracker.ui.screens.profile.settings.*

@Composable
fun MedTrackerNavGraph(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: Routes = Routes.UserDashboard,
) {
  
  NavHost(
    navController = navController,
    // startDestination = Routes.Login,
    startDestination = startDestination,
    modifier = modifier
      .fillMaxSize()
      .windowInsetsPadding(WindowInsets.safeGestures)
    //.windowInsetsPadding(WindowInsets.safeGestures)
  
  ) {
    
    // Home route with login, signup, and password reset actions
    composable<Routes.Login> {
      LoginScreen(
        onLoginClick = { userType ->
          when (userType) {
            UserType.PATIENT -> navController.navigate(Routes.UserDashboard)
            UserType.DOCTOR -> navController.navigate(Routes.DocDashboard)
            UserType.ADMIN -> navController.navigate(Routes.AdminDashboard)
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
    
    composable<Routes.UserDashboard> {
      DashboardScreen(
        onLogsClick = {
          navController.navigate(Routes.LogScreen)
        }
      )
    }
    
    composable<Routes.DocDashboard> {
      DocDashboardScreen(
        /* onLogsClick = {
          navController.navigate(Routes.LogScreen)
        } */
      )
    }
    
    composable<Routes.AdminDashboard> {
      DBDataScreen(
        /* onLogsClick = {
          navController.navigate(Routes.LogScreen)
        } */
      )
    }
    
    composable<Routes.LogScreen> {
      LogScreen(
        onGoBackClick = {
          navController.popBackStack()
        }
      )
    }
    
    composable<Routes.Calendar> {
      // TODO: Calendar
    }
    
    composable<Routes.Medications> {
      MedicationsScreen(
        onAddMedClick = { navController.navigate(Routes.NewMedication) },
        onViewMedClick = { medName ->
          navController.navigate(Routes.ViewMedication(medicationName = medName))
        })
    }
    composable<Routes.NewMedication> {
      NewMedicationDataScreen(
        onConfirmClick = { navController.navigate(Routes.Medications) }
      )
    }
// TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CHECK THIS
    composable<Routes.ViewMedication> { backStackEntry ->
      // Retrieve and pass the argument.
      val args = backStackEntry.toRoute<Routes.ViewMedication>()
      
      UpdateMedScreen(
        passedMedName = args.medicationName ?: "",
        onConfirmEdit = {
          navController.navigate(Routes.Medications)
        }
      )
    }
    
    composable<Routes.Profile> {
      ProfileScreen(
        onSettingsClick = { navController.navigate(Routes.AppSettings) },
        onNotificationsClick = { navController.navigate(Routes.NotificationsSettings) }
      )
    }
    
    composable<Routes.AppSettings> { SettingsScreen() }
    
    composable<Routes.NotificationsSettings> { NotificationsSettingsScreen() }
  }
}