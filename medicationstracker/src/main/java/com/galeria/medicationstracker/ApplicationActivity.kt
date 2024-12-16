package com.galeria.medicationstracker

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.vector.*
import androidx.core.view.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.model.*
import com.galeria.medicationstracker.model.navigation.*
import com.galeria.medicationstracker.ui.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.screens.dashboard.*
import com.galeria.medicationstracker.ui.screens.medications.*
import com.galeria.medicationstracker.ui.screens.medications.logs.*
import com.galeria.medicationstracker.ui.screens.medications.update.*
import com.galeria.medicationstracker.ui.screens.profile.*
import com.galeria.medicationstracker.ui.screens.profile.notifications.*
import com.galeria.medicationstracker.ui.screens.profile.settings.*
import com.galeria.medicationstracker.ui.theme.*
import java.time.*

class ApplicationActivity : ComponentActivity() {
  
  private val headViewModel: HeadViewModel by viewModels()
  
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    WindowCompat.setDecorFitsSystemWindows(window, false)
    
    setContent {
      MedTrackerTheme {
        val navController = rememberNavController()
        
        val items = bottomNavItems()
        
        val formattedCurrentDate = getFormattedDate(LocalDate.now())
        
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
                  .padding(it)/* .padding(horizontal = 16.dp) */
            
            ) {
              
              composable<Routes.UserDashboard> {
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
        route = Routes.UserDashboard,
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
}
