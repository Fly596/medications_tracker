package com.galeria.medicationstracker.model.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    // region Autehntication
    @Serializable data object Home : Routes()
    @Serializable data object Registration : Routes()
    // endregion

  // import com.google.firebase.firestore.ktx.toObjects
    // region Application
    @Serializable data object Dashboard : Routes()
    @Serializable data object Profile : Routes()
    @Serializable data object Calendar : Routes()
    @Serializable data object Medications : Routes()

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
    // endregion
}
