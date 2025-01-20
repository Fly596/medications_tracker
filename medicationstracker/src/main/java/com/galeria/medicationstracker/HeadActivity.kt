package com.galeria.medicationstracker

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.navigation.compose.*
import com.galeria.medicationstracker.ui.*
import com.galeria.medicationstracker.ui.theme.*
import com.galeria.medicationstracker.utils.navigation.*
import com.google.firebase.auth.*
import kotlinx.coroutines.*

class HeadActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private val startDestinations = listOf(
        Routes.NavigationRoutes.AUTH,
        Routes.NavigationRoutes.PATIENT_DASHBOARD,
    )
    private var currentDestination: String = startDestinations.get(0)
    
    private val headViewModel: HeadViewModel by viewModels()
    
    override fun onStart() {
        super.onStart()
        
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            currentDestination = startDestinations.get(1)
        } else {
            currentDestination = startDestinations.get(0)
        }
    }
    
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            enableEdgeToEdge()
            
            val navController = rememberNavController()
            MedTrackerTheme {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                val scope = rememberCoroutineScope()
                ObserveAsEvents(
                    flow = SnackbarController.events,
                    snackbarHostState
                ) { event ->
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
                    containerColor = MedTrackerTheme.colors.secondaryBackground,
                    
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination =
                            navBackStackEntry?.destination?.route
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
                    
                    ) {
                    ApplicationNavHost(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .padding(horizontal = 16.dp),
                        navController = navController,
                        startDestination = currentDestination
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