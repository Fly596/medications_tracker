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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.getStringFormattedDate
import com.galeria.medicationstracker.model.navigation.ApplicationNavHost
import com.galeria.medicationstracker.model.navigation.Routes.NavigationRoutes
import com.galeria.medicationstracker.ui.HeadViewModel
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import kotlinx.coroutines.launch
import java.time.LocalDate

class HeadActivity : ComponentActivity() {

    private val headViewModel: HeadViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            MedTrackerTheme {

                val items = bottomNavItems()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.displayCutout),
                    containerColor = MedTrackerTheme.colors.secondaryBackground,

                    topBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination?.route

                        val routeTitles = mapOf(
                            NavigationRoutes.USER_DASHBOARD to {
                                "Today, ${
                                    getStringFormattedDate(
                                        LocalDate.now()
                                    )
                                }"
                            },
                            NavigationRoutes.MEDICATIONS to { "My Meds" },
                            NavigationRoutes.USER_DASHBOARD to { "Dashboard" },
                            NavigationRoutes.PROFILE to { "My Profile" },
                            NavigationRoutes.NEW_MEDICATION to { "Add medication" },
                            NavigationRoutes.APP_SETTINGS to { "App Settings" },
                            NavigationRoutes.NOTIFICATIONS_SETTINGS to { "Notifications Settings" },
                            NavigationRoutes.DOC_DASHBOARD to { "Welcome, Doctor" },
                            NavigationRoutes.DOC_PATIENTS_LIST to { "List of Patients" },
                            NavigationRoutes.ADMIN_DASHBOARD to { "Hello, Admin" },
                        )

                        val routesWithoutTopBar = listOf(
                            NavigationRoutes.LOGIN,
                            NavigationRoutes.REGISTRATION,
                            NavigationRoutes.PASSWORD_RECOVERY
                        )
                        val title = routeTitles[currentDestination]?.invoke()

                        if (title != null && currentDestination !in routesWithoutTopBar) {
                            FlyTopAppBar(title = title)
                        }

                    },
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination?.route
                        val routesWithoutBottomBar = listOf(
                            NavigationRoutes.LOGIN,
                            NavigationRoutes.REGISTRATION,
                            NavigationRoutes.PASSWORD_RECOVERY
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
                            .padding(it)
                            .padding(horizontal = 16.dp),
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