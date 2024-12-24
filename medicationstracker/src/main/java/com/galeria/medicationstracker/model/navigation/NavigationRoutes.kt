package com.galeria.medicationstracker.model.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.galeria.medicationstracker.ui.screens.auth.accountrecovery.AccountRecoveryScreen
import com.galeria.medicationstracker.ui.screens.auth.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.auth.signup.SignupScreen
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardVM
import com.galeria.medicationstracker.ui.screens.medications.MedicationsScreen
import com.galeria.medicationstracker.ui.screens.medications.MedicationsViewModel
import com.galeria.medicationstracker.ui.screens.medications.NewMedicationDataScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    object AuthGraph : Routes()

    @Serializable
    data object Login : Routes()

    /*     @Serializable
        data class Registration(val email: String?) : Routes() */
    @Serializable
    data object Registration : Routes()

    @Serializable
    data object PasswordRecovery : Routes()
    /*     @Serializable
        data class PasswordRecovery(val email: String?) : Routes() */

    @Serializable
    object UserGraph : Routes()

    @Serializable
    data object UserHome : Routes()

    @Serializable
    object UserMedsGraph : Routes()

    @Serializable
    data object UserMedications : Routes()

    @Serializable
    data object NewMedication : Routes()

    @Serializable
    data class ViewMedication(val medicationName: String?) : Routes()

    @Serializable
    data object UserProfile : Routes()

    @Serializable
    data object LogScreen : Routes()

    @Serializable
    data object LogsScreen : Routes()

    @Serializable
    data object AppSettings : Routes()

    @Serializable
    data object NotificationsSettings : Routes()

    @Serializable
    data object Calendar : Routes()

    // region Admin Screens.
    // экран с данными всех таблиц БД.
    @Serializable
    data object AdminDashboard : Routes()

    // endregion
    // region Doctor Screens.
    @Serializable
    data object DocDashboard : Routes()

    @Serializable
    data object PatientsList : Routes()

    // endregion
    //@Serializable data object ViewMedication : Routes()

    // region later
    @Serializable
    data object NewMedicationName : Routes()

    @Serializable
    data object NewMedicationForm : Routes()

    @Serializable
    data object NewMedicationStrength : Routes()

    @Serializable
    data object NewMedicationFrequency : Routes()

    @Serializable
    data object NewMedicationReminder : Routes()

    @Serializable
    data object NewMedicationReview : Routes()

    // endregion
    object NavigationRoutes {

        const val USER_DASHBOARD =
            "com.galeria.medicationstracker.model.navigation.Routes.UserHome"
        const val MEDICATIONS =
            "com.galeria.medicationstracker.model.navigation.Routes.UserMedications"
        const val PROFILE =
            "com.galeria.medicationstracker.model.navigation.Routes.UserProfile"
        const val NEW_MEDICATION =
            "com.galeria.medicationstracker.model.navigation.Routes.NewMedication"
        const val APP_SETTINGS =
            "com.galeria.medicationstracker.model.navigation.Routes.AppSettings"
        const val NOTIFICATIONS_SETTINGS =
            "com.galeria.medicationstracker.model.navigation.Routes.NotificationsSettings"
        const val DOC_DASHBOARD =
            "com.galeria.medicationstracker.model.navigation.Routes.DocDashboard"
        const val DOC_PATIENTS_LIST =
            "com.galeria.medicationstracker.model.navigation.Routes.PatientsList"
        const val ADMIN_DASHBOARD =
            "com.galeria.medicationstracker.model.navigation.Routes.AdminDashboard"
        const val LOGIN =
            "com.galeria.medicationstracker.model.navigation.Routes.Login"
        const val REGISTRATION =
            "com.galeria.medicationstracker.model.navigation.Routes.Registration"
        const val PASSWORD_RECOVERY =
            "com.galeria.medicationstracker.model.navigation.Routes.PasswordRecovery"
        // ... other routes
    }
}

@Composable
fun ApplicationNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Routes = Routes.AuthGraph
) {
    val dashboardVM: DashboardVM = viewModel()
    val medicationsViewModel: MedicationsViewModel = viewModel()

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        authGraph(navController)
        appGraph(navController, dashboardVM)
        userMedsGraph(navController)
    }
}

// Граф для страниц аутификации.
fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation<Routes.AuthGraph>(startDestination = Routes.Login) {
        composable<Routes.Login> {
            LoginScreen(
                onLogin = {
                    navController.navigate(route = Routes.UserHome)
                },
                onRegistration = {
                    navController.navigate(Routes.Registration)
                },
                onResetPassword = {
                    navController.navigate(Routes.PasswordRecovery)
                },
            )
        }
        composable<Routes.Registration> {
            SignupScreen(
                navigateHome = {
                    navController.navigate(Routes.Login)
                }
            )
        }
        composable<Routes.PasswordRecovery> {
            AccountRecoveryScreen(
                navigateHome = {
                    navController.navigate(Routes.Login)
                }
            )
            // TODO: Password recovery screen
        }
    }
}

// Граф для страниц приложения.
fun NavGraphBuilder.appGraph(navController: NavHostController, viewModel: DashboardVM) {
    navigation<Routes.UserGraph>(startDestination = Routes.UserHome) {

        composable<Routes.UserHome> {
            DashboardScreen(
                dashboardViewModel = viewModel,
                onMedicationLogsClick = {/*TODO: navController.navigate(Routes.LogScreen) */ },
            )
        }

        userMedsGraph(navController)
        /*         composable<Routes.UserMedications> {
                    MedicationsScreen(
                        dashboardViewModel = viewModel,

                        onViewMedClick = { medName ->
                            navController.navigate(Routes.ViewMedication(medName))
                        }
                    )
                } */

        composable<Routes.UserProfile> {
            ProfileScreen(
                dashboardViewModel = viewModel,

                )
        }
    }
}

// Граф для страницы с лекарствами.
fun NavGraphBuilder.userMedsGraph(
    navController: NavHostController,
    // viewModel: MedicationsViewModel
) {
    navigation<Routes.UserMedsGraph>(startDestination = Routes.UserMedications) {
        composable<Routes.UserMedications> {
            MedicationsScreen(
                // medicationsViewModel = viewModel,
                onAddMedClick = {
                    navController.navigate(Routes.NewMedication)
                },
                onViewMedClick = {
                    // TODO: navController.navigate(Routes.ViewMedication(it))
                }
            )
        }

        composable<Routes.NewMedication> {
            NewMedicationDataScreen(
                onConfirmClick = {
                    navController.navigate(Routes.UserMedications)
                },
                // medsViewModel = viewModel
            )
        }
    }
}