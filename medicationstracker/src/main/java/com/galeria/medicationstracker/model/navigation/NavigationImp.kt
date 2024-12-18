package com.galeria.medicationstracker.model.navigation

import kotlinx.serialization.*


@Serializable
sealed class Routes {
  
  object NavigationRoutes {
    
    const val USER_DASHBOARD =
      "com.galeria.medicationstracker.model.navigation.Routes.UserDashboard"
    const val MEDICATIONS =
      "com.galeria.medicationstracker.model.navigation.Routes.Medications"
    const val PROFILE =
      "com.galeria.medicationstracker.model.navigation.Routes.Profile"
    const val NEW_MEDICATION =
      "com.galeria.medicationstracker.model.navigation.Routes.NewMedication"
    const val APP_SETTINGS =
      "com.galeria.medicationstracker.model.navigation.Routes.AppSettings"
    const val NOTIFICATIONS_SETTINGS =
      "com.galeria.medicationstracker.model.navigation.Routes.NotificationsSettings"
    const val DOC_DASHBOARD =
      "com.galeria.medicationstracker.model.navigation.Routes.DocDashboard"
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
  
  // region Autehntication
  @Serializable
  data object Login : Routes()
  
  @Serializable
  data class Registration(val email: String?) : Routes()
  
  @Serializable
  data class PasswordRecovery(val email: String?) : Routes()
  // endregion
  
  // region Admin Screens.
  
  // экран с данными всех таблиц БД.
  @Serializable
  data object AdminDashboard : Routes()
  
  // endregion
  
  // region Doctor Screens.
  @Serializable
  data object DocDashboard : Routes()
  
  // endregion
  
  // region Application
  @Serializable
  data object UserDashboard : Routes()
  
  @Serializable
  data object LogScreen : Routes()
  
  @Serializable
  data object Profile : Routes()
  
  @Serializable
  data object AppSettings : Routes()
  
  @Serializable
  data object NotificationsSettings : Routes()
  
  @Serializable
  data object Calendar : Routes()
  
  @Serializable
  data object Medications : Routes()
  
  @Serializable
  data object NewMedication : Routes()
  
  //@Serializable data object ViewMedication : Routes()
  @Serializable
  data class ViewMedication(val medicationName: String?) : Routes()
  
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
}

