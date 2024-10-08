package com.galeria.medicationstracker.model.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    // region Autehntication
    @Serializable data object Home : Routes()
    @Serializable data object Registration : Routes()
    // endregion

    // region Application
    @Serializable data object Dashboard : Routes()
    @Serializable data object Profile : Routes()
    @Serializable data object Calendar : Routes()
    @Serializable data object Medications : Routes()
    // endregion
}
