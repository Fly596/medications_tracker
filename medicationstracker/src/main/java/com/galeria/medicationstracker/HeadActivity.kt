package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.screens.autentification.create_account.SignupScreen
import com.galeria.medicationstracker.ui.screens.autentification.create_account.SignupScreenViewModel
import com.galeria.medicationstracker.ui.screens.autentification.login.LoginScreen
import com.galeria.medicationstracker.ui.screens.autentification.login.LoginScreenViewModel
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import com.galeria.medicationstracker.ui.shared.components.HeadViewModel
import com.galeria.medicationstracker.ui.theme.SpeechRecognitionAppTheme
import com.google.firebase.FirebaseApp

data class BottomNavigationItem(
    val title: String,
    val route: Routes,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

class HeadActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        val loginViewModel = LoginScreenViewModel()
        val registerViewModel = SignupScreenViewModel()
        val headViewModel = HeadViewModel()

        enableEdgeToEdge()
        setContent {
            SpeechRecognitionAppTheme {
                val navController = rememberNavController()

                val items = listOf(
                    BottomNavigationItem(
                        title = "Dashboard",
                        route = Routes.Dashboard,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Calendar",
                        route = Routes.Calendar,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),

                    BottomNavigationItem(
                        title = "Medications",
                        route = Routes.Medications,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false,
                        badgeCount = 16
                    ),

                    BottomNavigationItem(
                        title = "Profile",
                        route = Routes.Profile,
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = true
                    )
                )

                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    bottomBar = {
                        BottomNavBar(items, /* selectedItemIndex,  */navController, headViewModel)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // region Autehntication
                        composable<Routes.Home> {
                            LoginScreen(
                                onLoginClick = {
                                    navController.navigate(
                                        Routes.Dashboard/* (
                                            userEmail = "test"
                                        ) */
                                    )
                                },
                                onSignupClick = {
                                    navController.navigate(
                                        Routes.Registration
                                    )
                                },
                                viewModel = loginViewModel,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp)
                            )
                        }

                        composable<Routes.Registration> {
                            SignupScreen(
                                onCreateAccountClick = {
                                    navController.navigate(
                                        Routes.Home
                                    )
                                },
                                viewModel = registerViewModel,
                                // viewModelLogin = viewModel,
                            )
                        }
                        // endregion

                        // region Application
                        composable<Routes.Dashboard> { /* backStackEntry -> */
                            // val args = backStackEntry.toRoute<Routes.Summary>()
                            DashboardScreen(
                                // args.userEmail,
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
/*                             ProfileScreen(
                                onNavigateToCalendar = {
                                    navController.navigate(
                                        Routes.Home
                                    )
                                }
                            ) */
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpeechRecognitionAppTheme {
        // Greeting("Android")
    }
}