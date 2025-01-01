package com.galeria.medicationstracker.utils.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.galeria.medicationstracker.ui.screens.auth.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object Authentificate

@Serializable
object Login

@Serializable
object Registration

@Serializable
object ForgotPassword

// TODO: Add navigation.
fun NavGraphBuilder.authDestination() {
    navigation<Authentificate>(startDestination = Login) {
        composable<Login> {
            LoginScreen(
                onLogin = {},
                onRegistration = {},
                onResetPassword = {},
            )
        }
        composable<Registration> {
        }
        composable<ForgotPassword> {
        }
    }
}