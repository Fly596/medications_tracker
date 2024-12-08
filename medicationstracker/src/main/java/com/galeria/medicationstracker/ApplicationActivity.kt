package com.galeria.medicationstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.galeria.medicationstracker.ui.HeadViewModel
import com.galeria.medicationstracker.ui.screens.dashboard.DashboardScreen
import com.galeria.medicationstracker.ui.screens.medications.MedicationsScreen
import com.galeria.medicationstracker.ui.screens.medications.NewMedicationDataScreen
import com.galeria.medicationstracker.ui.screens.medications.update.UpdateMedScreen
import com.galeria.medicationstracker.ui.screens.profile.ProfileScreen
import com.galeria.medicationstracker.ui.screens.profile.notifications.NotificationsSettingsScreen
import com.galeria.medicationstracker.ui.screens.profile.settings.SettingsScreen
import com.galeria.medicationstracker.ui.theme.MedTrackerTheme

data class BottomNavigationItem(
  val title: String,
  val route: Routes,
  val selectedIcon: ImageVector,
  val unselectedIcon: ImageVector,
  val hasNews: Boolean,
  val badgeCount: Int? = null,
)

class ApplicationActivity : ComponentActivity() {

  private val headViewModel: HeadViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //enableEdgeToEdge()

    setContent {
      MedTrackerTheme {
        val navController = rememberNavController()

        // Bottom navigation items.
        val items = bottomNavItems()

        Scaffold(
          modifier = Modifier,
          containerColor = MedTrackerTheme.colors.primaryBackground,
          bottomBar = {
            BottomNavBar(items, navController, headViewModel)
          },
        ) { innerPadding ->
          NavHost(
            navController = navController,
            startDestination = Routes.Dashboard,
            modifier =
              Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(start = 24.dp, end = 24.dp, top = 16.dp),
          ) {

            composable<Routes.Dashboard> {
              DashboardScreen(
              )
            }

            composable<Routes.Calendar> {
              // TODO: Calendar
            }

            composable<Routes.Medications> {
              MedicationsScreen(
                onAddMedClick = { navController.navigate(Routes.NewMedication) },
                onOpenMedClick = {
                  navController.navigate(Routes.ViewMedication)
                })
            }

            composable<Routes.NewMedication> {
              NewMedicationDataScreen(
                onConfirmClick = { navController.navigate(Routes.Medications) }
              )
            }

            composable<Routes.ViewMedication> {
              UpdateMedScreen(onConfirmEdit = { navController.navigate(Routes.Medications) })
            }

            composable<Routes.Profile> {
              ProfileScreen(
                onSettingsClick = { navController.navigate(Routes.AppSettings) },
                onNotificationsClick = {navController.navigate(Routes.NotificationsSettings)}
              )
            }

            composable<Routes.AppSettings> { SettingsScreen() }

            composable<Routes.NotificationsSettings> { NotificationsSettingsScreen() }
          }
        }
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

    // TODO: Change colors
    NavigationBar(
      modifier = Modifier.fillMaxWidth(),
      containerColor = MedTrackerTheme.colors.secondaryBackground,
      contentColor = MedTrackerTheme.colors.secondary600,
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
