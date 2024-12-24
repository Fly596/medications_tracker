package com.galeria.medicationstracker.model.navigation

/*
@Serializable
sealed class Screens {

  @Serializable
  object Auth {

    @Serializable
    object SignIn : Screens()

    @Serializable
    object Registration : Screens()

    @Serializable
    object RecoverPassword : Screens()
  }

  @Serializable
  object UserApp : Screens() {

    @Serializable
    object UserHome : Screens()

    @Serializable
    object UserMedications : Screens()

    @Serializable
    object UserProfile : Screens()

  }
}

@Composable
fun NestedNavStartDestination() {
  val flyNavController = rememberNavController()

  NavHost(
    flyNavController,
    startDestination = Screens.Auth
  ) {
    authGraph(flyNavController)
    appGraph(flyNavController)
  }

}

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
) {
  navigation<Screens.Auth>(
    startDestination = Screens.Auth.SignIn
  ) {
    composable<Screens.Auth.SignIn> {
      LoginScreen(
        onSignInClickNewNaw = {
          navController.popBackStack()
          navController.navigate(Screens.UserApp.UserHome)
        },
        onSignupClickNew = {
          navController.popBackStack()
          navController.navigate(Screens.Auth.Registration)
        },
        onResetPasswordClickNew = {

          navController.popBackStack()
          navController.navigate(Screens.Auth.RecoverPassword)
        }
      )
    }

    composable<Screens.Auth.Registration> {
      SignupScreen(
        navigateHome = {
          navController.popBackStack()
          navController.navigate(Screens.Auth.SignIn)
        }
      )
    }


    composable<Screens.Auth.RecoverPassword> {
      AccountRecoveryScreen(
        navigateHome = {
          navController.popBackStack()
          navController.navigate(Screens.Auth.SignIn)
        }
      )
    }
  }
}

fun NavGraphBuilder.appGraph(
    navController: NavHostController,
) {
  navigation<Screens.UserApp>(
    startDestination = Screens.UserApp.UserHome::class
  ) {

    composable<Screens.UserApp.UserHome> {
      DashboardScreen(
      )
    }

    composable<Screens.UserApp.UserMedications> {
      MedicationsScreen(
      )
    }

    composable<Screens.UserApp.UserProfile> {
      ProfileScreen()
    }
  }
}*/
