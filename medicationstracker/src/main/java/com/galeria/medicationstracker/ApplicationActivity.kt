package com.galeria.medicationstracker

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.galeria.medicationstracker.ui.HeadViewModel
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import com.galeria.medicationstracker.utils.navigation.Routes

/*
class ApplicationActivity : ComponentActivity() {

  // private val headViewModel: HeadViewModel by viewModels()

     @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      WindowCompat.setDecorFitsSystemWindows(window, false)

      setContent {
        MedTrackerTheme {
          val navController = rememberNavController()

          val items = bottomNavItems()

          val formattedCurrentDate = getStringFormattedDate(LocalDate.now())

          Scaffold(
            modifier = Modifier
              .windowInsetsPadding(WindowInsets.displayCutout),

            topBar = {
              val navBackStackEntry by navController.currentBackStackEntryAsState()
              val currentDestination = navBackStackEntry?.destination?.route

              // if (shouldDisplayTopBar(currentDestination)) {
              val title = when (currentDestination) {
                "com.galeria.medicationstracker.model.navigation.Routes.Dashboard" -> "Today, $formattedCurrentDate"
                "com.galeria.medicationstracker.model.navigation.Routes.Medications" -> "My Meds"
                "com.galeria.medicationstracker.model.navigation.Routes.Profile" -> "My Profile"
                "com.galeria.medicationstracker.model.navigation.Routes.NewMedication" -> "Add medication"
                "com.galeria.medicationstracker.model.navigation.Routes.AppSettings" -> "App Settings"
                "com.galeria.medicationstracker.model.navigation.Routes.NotificationsSettings" -> "Notifications Settings"
                else -> null
              }

              FlyTopAppBar(
                title = title.toString()
              )
              //}

            },
            containerColor = MedTrackerTheme.colors.secondaryBackground,
            bottomBar = {
              BottomNavBar(items, navController, headViewModel)
            },

            content = {
              NavHost(
                navController = navController,
                startDestination = Routes.UserDashboard,
                modifier =
                  Modifier
                    .fillMaxSize()
                    .padding(it) *//* .padding(horizontal = 16.dp) *//*
            
            ) {
              
              composable<Routes.UserDashboard> {
                DashboardScreen(
                  onMedicationLogsClick = {
                    navController.navigate(Routes.LogScreen)
                  }
                )
              }
              
              composable<Routes.LogScreen> {
                LogScreen(
                  onGoBackClick = {
                    navController.popBackStack()
                  }
                )
              }
              
              composable<Routes.Calendar> {
                // TODO: Calendar
              }
              
              composable<Routes.Medications> {
                MedicationsScreen(
                  onAddMedClick = { navController.navigate(Routes.NewMedication) },
                  onViewMedClick = { medName ->
                    navController.navigate(Routes.ViewMedication(medicationName = medName))
                  })
              }
              
              composable<Routes.NewMedication> {
                NewMedicationDataScreen(
                  onConfirmClick = { navController.navigate(Routes.Medications) }
                )
              }
// TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CHECK THIS
              composable<Routes.ViewMedication> { backStackEntry ->
                // Retrieve and pass the argument.
                val args = backStackEntry.toRoute<Routes.ViewMedication>()
                
                UpdateMedScreen(
                  passedMedName = args.medicationName ?: "",
                  onConfirmEdit = {
                    navController.navigate(Routes.Medications)
                  }
                )
              }
              
              composable<Routes.Profile> {
                ProfileScreen(
                  onSettingsClick = { navController.navigate(Routes.AppSettings) },
                  onNotificationsClick = { navController.navigate(Routes.NotificationsSettings) }
                )
              }
              
              composable<Routes.AppSettings> { SettingsScreen() }
              
              composable<Routes.NotificationsSettings> { NotificationsSettingsScreen() }
            }
          }
        )
      }
    }
  }
}
*/
data class BottomNavItem(
    val title: String,
    val route: Routes.PatientRoutes,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
)

fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
        BottomNavItem(
            title = "Dashboard",
            route = Routes.PatientRoutes.PatientHome,
            selectedIcon = Icons.Filled.Dashboard,
            unselectedIcon = Icons.Outlined.Dashboard
        ),
        BottomNavItem(
            title = "Medications",
            route = Routes.PatientRoutes.PatientMedications,
            selectedIcon = Icons.Filled.Medication,
            unselectedIcon = Icons.Outlined.Medication,
            hasNews = false,
            badgeCount = 16,
        ),
        BottomNavItem(
            title = "Profile",
            route = Routes.PatientRoutes.PatientProfile,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = false,
        ),
        // ... (other items)
    )
}

@Composable
fun BottomNavBar(
    navItems: List<BottomNavItem>,
    navController: NavHostController,
    viewModel: HeadViewModel,
) {
    val currentNavItemIndex = viewModel.selectedItemIndex.collectAsState().value

    Column {
        NavigationBar(
            // modifier = Modifier.fillMaxWidth(),
            containerColor = MedTrackerTheme.colors.primaryBackground,
            contentColor = MedTrackerTheme.colors.primaryLabel,
        ) {
            navItems.forEachIndexed { navItemIndex, navItem ->
                NavigationBarItem(
                    selected = currentNavItemIndex == navItemIndex,
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MedTrackerTheme.colors.primaryTinted
                    ),
                    onClick = {
                        viewModel.updateSelectedItemIndex(navItemIndex)
                        navController.navigate(navItem.route)
                    },
                    label = {
                        Text(
                            text = navItem.title,
                            style = MedTrackerTheme.typography.body
                        )
                    },
                    icon = {
                        IconWithBadge(
                            icon = if (navItemIndex == currentNavItemIndex) navItem.selectedIcon else navItem.unselectedIcon,
                            badgeCount = navItem.badgeCount,
                            showUnreadBadge = navItem.hasNews,
                            contentDescription = navItem.title,
                        )
                    },
                )
            }
        }
    }
    // TODO: Change colors

}

@Composable
fun IconWithBadge(
    icon: ImageVector,
    badgeCount: Int?,
    showUnreadBadge: Boolean,
    contentDescription: String?,
) {
    BadgedBox(
        badge = {
            when {
                badgeCount != null -> Badge { Text(text = badgeCount.toString()) }
                showUnreadBadge -> Badge()
            }
        }
    ) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}

