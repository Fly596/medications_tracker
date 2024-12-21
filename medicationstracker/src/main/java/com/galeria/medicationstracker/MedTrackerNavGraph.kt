package com.galeria.medicationstracker

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.galeria.medicationstracker.data.UserType
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.admin.DBDataScreen
import com.galeria.medicationstracker.ui.doctor.home.DocDashboardScreen
import com.galeria.medicationstracker.ui.screens.auth.accountrecovery.AccountRecoveryScreen
import com.galeria.medicationstracker.ui.screens.auth.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.auth.signup.SignupScreen
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.dashboard.record.RecordsScreen
import com.galeria.medicationstracker.ui.screens.medications.MedicationsScreen
import com.galeria.medicationstracker.ui.screens.medications.NewMedicationDataScreen
import com.galeria.medicationstracker.ui.screens.medications.logs.LogScreen
import com.galeria.medicationstracker.ui.screens.medications.update.UpdateMedScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import com.galeria.medicationstracker.ui.screens.profile.notifications.NotificationsSettingsScreen
import com.galeria.medicationstracker.ui.screens.profile.settings.SettingsScreen

@Composable
fun MedTrackerNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Routes = Routes.Login,
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
        onMedicationLogsClick = {
          navController.navigate(Routes.RecordsScreen)
        }
      )
    }
    composable<Routes.RecordsScreen> {
      RecordsScreen(
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
          navController.popBackStack()
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