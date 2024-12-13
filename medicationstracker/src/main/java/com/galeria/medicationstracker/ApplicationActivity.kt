package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.galeria.medicationstracker.model.navigation.Routes
import com.galeria.medicationstracker.ui.HeadViewModel
import com.galeria.medicationstracker.ui.components.FlyTopAppBar
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.medications.MedicationsScreen
import com.galeria.medicationstracker.ui.screens.medications.NewMedicationDataScreen
import com.galeria.medicationstracker.ui.screens.medications.logs.LogScreen
import com.galeria.medicationstracker.ui.screens.medications.update.UpdateMedScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import com.galeria.medicationstracker.ui.screens.profile.notifications.NotificationsSettingsScreen
import com.galeria.medicationstracker.ui.screens.profile.settings.SettingsScreen
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ApplicationActivity : ComponentActivity() {

  private val headViewModel: HeadViewModel by viewModels()

  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    WindowCompat.setDecorFitsSystemWindows(window, false)
    // enableEdgeToEdge()

    setContent {
      MedTrackerTheme {
        val navController = rememberNavController()

        val items = bottomNavItems()

        val currentDate = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("MMM dd")
        val formattedCurrentDate = currentDate.format(dateFormatter)

        Scaffold(
          modifier = Modifier
            .windowInsetsPadding(WindowInsets.displayCutout),
          topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route
            val title = when (currentDestination) {
              "com.galeria.medicationstracker.model.navigation.Routes.Dashboard" -> "Today, $formattedCurrentDate"
              "com.galeria.medicationstracker.model.navigation.Routes.Medications" -> "My Meds"
              "com.galeria.medicationstracker.model.navigation.Routes.Profile" -> "My Profile"
              else -> null
            }
            FlyTopAppBar(
              title = title.toString()
            )
          },
          containerColor = MedTrackerTheme.colors.secondaryBackground,
          bottomBar = {
            BottomNavBar(items, navController, headViewModel)
          },

          content = {
            NavHost(
              navController = navController,
              startDestination = Routes.Dashboard,
              modifier =
                Modifier
                  .fillMaxSize()
                  .padding(it)/* .padding(horizontal = 16.dp) */

            ) {

              composable<Routes.Dashboard> {
                DashboardScreen(
                  onLogsClick = {
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

  data class BottomNavItem(
    val title: String,
    val route: Routes,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean = false,
    val badgeCount: Int? = null
  )

  private fun bottomNavItems(): List<BottomNavItem> {
    return listOf(
      BottomNavItem(
        title = "Dashboard",
        route = Routes.Dashboard,
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard
      ),
      BottomNavItem(
        title = "Medications",
        route = Routes.Medications,
        selectedIcon = Icons.Filled.Medication,
        unselectedIcon = Icons.Outlined.Medication,
        hasNews = false,
        badgeCount = 16,
      ),
      BottomNavItem(
        title = "Profile",
        route = Routes.Profile,
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
      ),
      // ... (other items)
    )
  }

  @Composable
  private fun BottomNavBar(
    navItems: List<BottomNavItem>,
    navController: NavHostController,
    viewModel: HeadViewModel,
  ) {
    val currentNavItemIndex = viewModel.selectedItemIndex.collectAsState().value

    Column {
      HorizontalDivider()

      NavigationBar(
        // modifier = Modifier.fillMaxWidth(),
        containerColor = MedTrackerTheme.colors.secondaryBackgroundGrouped,
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
            label = { Text(text = navItem.title) },
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
}
