package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import com.galeria.medicationstracker.ui.shared.components.HeadViewModel
import com.galeria.medicationstracker.ui.theme.MedicationsTrackerAppTheme
import com.google.firebase.FirebaseApp

data class BottomNavigationItem(
    val title: String,
    val route: Routes,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

class ApplicationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        val headViewModel = HeadViewModel()

        enableEdgeToEdge()
        setContent {
            MedicationsTrackerAppTheme {
                val navController = rememberNavController()

                val items = listOf(
                    BottomNavigationItem(
                        title = "Dashboard",
                        route = Routes.Dashboard,
                        selectedIcon = Icons.Filled.Dashboard,
                        unselectedIcon = Icons.Outlined.Dashboard,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Calendar",
                        route = Routes.Calendar,
                        selectedIcon = Icons.Filled.CalendarMonth,
                        unselectedIcon = Icons.Outlined.CalendarMonth,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Medications",
                        route = Routes.Medications,
                        selectedIcon = Icons.Filled.Medication,
                        unselectedIcon = Icons.Outlined.Medication,
                        hasNews = false,
                        badgeCount = 16
                    ),

                    BottomNavigationItem(
                        title = "Profile",
                        route = Routes.Profile,
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                        hasNews = true
                    )
                )

                Scaffold(
                    bottomBar = {
                        BottomNavBar(items, /* selectedItemIndex,  */navController, headViewModel)
                    },
                    // containerColor = MedicationsTrackerAppTheme.systemColors.backgroundLightPrimary
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Profile,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
                    ) {
                        // region Application
                        composable<Routes.Dashboard> {
                            DashboardScreen(
                                onProfileNavigate = {
                                    navController.navigate(
                                        Routes.Profile
                                    )
                                },
                                onCalendarNavigate = {
                                    navController.navigate(
                                        Routes.Calendar
                                    )
                                }
                            )
                        }

                        composable<Routes.Profile> {
                            ProfileScreen()
                        }

                        composable<Routes.Calendar> {
                            // TODO: Calendar
                        }

                        // endregion
                    }
                }
            }
        }
    }


    @Composable
    private fun BottomNavBar(
        items: List<BottomNavigationItem>,
        // selectedItemIndex: Int,
        navController: NavHostController,
        viewModel: HeadViewModel
    ) {
        // var selectedItemIndex1 = viewModel.selectedItemIndex.value
        val selectedItemIndex = viewModel.selectedItemIndex.collectAsState().value

        NavigationBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        viewModel.updateSelectedItemIndex(index)
                        // selectedItemIndex = index
                        navController.navigate(item.route)
                    },
                    label = { Text(text = item.title) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != null) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (index == selectedItemIndex) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title
                            )
                        }
                    }

                )
            }
        }
    }
}
