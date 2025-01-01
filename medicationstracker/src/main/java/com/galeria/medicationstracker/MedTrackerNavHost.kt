package com.galeria.medicationstracker

/*
@Composable
fun MedTrackerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Routes = Routes.Login,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeGestures)
    ) {

        composable<Routes.Login> {
            LoginScreen(
                */
/*                 onLoginClick = { userType ->
                                    when (userType) {
                                        UserType.PATIENT -> navController.navigate(Routes.UserHome)
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
                                } *//*

            )
        }

        composable<Routes.Registration> { backStackEntry ->

            // Retrieve and pass the email argument.
            val args = backStackEntry.toRoute<Routes.Registration>()
            SignupScreen(
                // passedEmail = args.email ?: "",
                navigateHome = { navController.navigate(Routes.Login) }
            )
        }

        // Password recovery screen route
        composable<Routes.PasswordRecovery> { backStackEntry ->
            // Retrieve and pass the email argument
            val args = backStackEntry.toRoute<Routes.PasswordRecovery>()
            AccountRecoveryScreen(
                // passedEmail = args.email ?: "",
                navigateHome = { navController.navigate(Routes.Login) },
            )
        }


        composable<Routes.UserHome> {
            DashboardScreen(
                onMedicationLogsClick = {
                    navController.navigate(Routes.LogsScreen)
                }
            )
        }
        composable<Routes.LogsScreen> {
            IntakeRecordsScreen(
            )
        }

        composable<Routes.DocDashboard> {
            DocDashboardScreen(
                */
/* onLogsClick = {
                  navController.navigate(Routes.LogScreen)
                } *//*

            )
        }


        composable<Routes.LogsScreen> {
            IntakeRecordsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }


        composable<Routes.UserMedications> {
            MedicationsScreen(
                onAddMedClick = { navController.navigate(Routes.NewMedication) },
                onEditMedClick = { medName ->
                    navController.navigate(Routes.EditMedication(medicationName = medName))
                },
                onViewMed = {*/
/* T *//*
 }
            )
        }
        composable<Routes.NewMedication> {
            NewMedicationDataScreen(
                onConfirmClick = { navController.navigate(Routes.UserMedications) }
            )
        }
        // TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CHECK THIS
        composable<Routes.EditMedication> { backStackEntry ->
            // Retrieve and pass the argument.
            val args = backStackEntry.toRoute<Routes.EditMedication>()

            UpdateMedScreen(
                passedMedName = args.medicationName ?: "",
                onConfirmEdit = {
                    navController.popBackStack()
                }
            )
        }

        composable<Routes.UserProfile> {
            ProfileScreen(
                onSettingsClick = { navController.navigate(Routes.AppSettings) },
                onNotificationsClick = { navController.navigate(Routes.NotificationsSettings) }
            )
        }

        composable<Routes.AppSettings> { SettingsScreen() }

        composable<Routes.NotificationsSettings> { NotificationsSettingsScreen() }
    }
}*/
/*

@Composable
fun ApplicationNavHost(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController(),
  startDestination: AuthRoutes = AuthRoutes.Auth,
) {
  val medsPagesVM: MedsPagesViewModel = viewModel()
  val medicationsViewModel: MedicationsViewModel = viewModel()

  NavHost(
    navController = navController,
    startDestination = AuthRoutes.Auth,
    modifier = modifier
      .fillMaxSize()
  ) {
    authGraph(navController)

    patientGraph(
      navController,
      medicationsViewModel,
      medsPagesVM
    )
    docGraph(
      navController
    )
    // userMedsGraph(navController)
  }
}*/
