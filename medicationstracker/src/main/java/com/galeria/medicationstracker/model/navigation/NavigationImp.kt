package com.galeria.medicationstracker.model.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {

    @Serializable
    data object Home : Routes()


    @Serializable
    data object Summary : Routes() /*TODO: добавить ввод имени.*/

    @Serializable
    data object Registration : Routes()
}
