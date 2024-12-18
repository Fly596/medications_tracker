package com.galeria.medicationstracker.ui.doctor

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.model.navigation.*
import com.galeria.medicationstracker.ui.components.*
import com.galeria.medicationstracker.ui.theme.*


class DoctorActivity : ComponentActivity() {
  
  
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    setContent {
      MedTrackerTheme {
        val navController = rememberNavController()
        
        val title by remember(navController) {
          derivedStateOf { // Use derivedStateOf to recalculate only when currentDestination changes
            when (navController.currentBackStackEntry?.destination?.route) {
              Routes.DocDashboard.toString() -> "Welcome!"
              else -> "MedTracker"
            }
          }
        }
        
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.displayCutout),
          topBar = {
            FlyTopAppBar(
              title = title.orEmpty()
            )
            
          },
          containerColor = MedTrackerTheme.colors.secondaryBackground,
          content = {
            DoctorNavHost(
              navController,
              paddingValues = it
            )
          }
        )
      }
    }
  }
  
}

@Composable
fun DoctorNavHost(
  navController: NavHostController,
  paddingValues: PaddingValues
) {
  NavHost(
    navController = navController,
    startDestination = Routes.DocDashboard,
    modifier = Modifier
      .fillMaxSize()
      .padding(paddingValues)
  ) {
    composable<Routes.DocDashboard> {
      DocDashboardScreen(modifier = Modifier)
    }
  }
}

@Preview
@Composable
fun DoctorNavHostPreview() {
  MedTrackerTheme {
    Surface {
      DoctorNavHost(
        navController = rememberNavController(),
        paddingValues = PaddingValues(0.dp)
      )
    }
  }
}