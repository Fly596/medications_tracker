package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.ui.HeadViewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.utils.navigation.ApplicationNavHost
import com.galeria.medicationstracker.utils.navigation.Routes
import kotlinx.coroutines.launch

class HeadActivity : ComponentActivity() {

    private val headViewModel: HeadViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MedTrackerTheme {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()
                ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
                    scope.launch {
                        snackbarHostState.currentSnackbarData?.dismiss()
                        val result = snackbarHostState.showSnackbar(
                            message = event.message,
                            actionLabel = event.action?.name,
                            duration = SnackbarDuration.Short,
                        )

                        if (result == SnackbarResult.ActionPerformed) {
                            event.action?.action?.invoke()
                        }
                    }
                }
                val items = bottomNavItems()

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.displayCutout),
                    containerColor = MedTrackerTheme.colors.primaryBackground,
                    /*                     topBar = {
                                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                                            val currentDestination = navBackStackEntry?.destination?.route
                                            val routeTitles = mapOf(
                                                Routes.NavigationRoutes.PATIENT_DASHBOARD to {
                                                    "Today, ${
                                                        getStringFormattedDate(
                                                            LocalDate.now()
                                                        )
                                                    }"
                                                },
                                                Routes.NavigationRoutes.PATIENT_MEDICATIONS to { "My Meds" },
                                                Routes.NavigationRoutes.PATIENT_DASHBOARD to { "Dashboard" },
                                                Routes.NavigationRoutes.PATIENT_PROFILE to { "My Profile" },
                                                Routes.NavigationRoutes.PATIENT_NEW_MEDICATION to { "Add medication" },
                                                Routes.NavigationRoutes.PATIENT_SETTINGS to { "App Settings" },
                                                Routes.NavigationRoutes.ADMIN_DASHBOARD to { "Hello, Admin" },
                                            )
                                            val routesWithoutTopBar = listOf(
                                                Routes.NavigationRoutes.LOGIN,
                                                Routes.NavigationRoutes.REGISTRATION,
                                                Routes.NavigationRoutes.PASSWORD_RECOVERY,
                                                Routes.NavigationRoutes.DOC_DASHBOARD,
                                                Routes.NavigationRoutes.DOC_PATIENTS_LIST
                                            )
                                            val title = routeTitles[currentDestination]?.invoke()

                                            if (title != null && currentDestination !in routesWithoutTopBar) {
                                                FlyTopAppBar(title = title)
                                            }
                                        }, */
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination?.route
                        val routesWithoutBottomBar = listOf(
                            Routes.NavigationRoutes.LOGIN,
                            Routes.NavigationRoutes.REGISTRATION,
                            Routes.NavigationRoutes.PASSWORD_RECOVERY,
                            Routes.NavigationRoutes.DOC_DASHBOARD,
                            Routes.NavigationRoutes.DOC_PATIENTS_LIST
                        )

                        if (currentDestination !in routesWithoutBottomBar) {
                            BottomNavBar(items, navController, headViewModel)

                        }
                    },
                    /* content = {
                        ApplicationNavHost(
                            navController = navController
                        )
                    } */
                ) {
                    ApplicationNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun SnackbarHandler(snackbarHostState: SnackbarHostState) {
    val scope = rememberCoroutineScope()
    ObserveAsEvents(
        flow = SnackbarController.events,
        snackbarHostState
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()
            val result =
                snackbarHostState.showSnackbar(
                    message = event.message,
                    actionLabel = event.action?.name,
                    duration = SnackbarDuration.Short,
                )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
}