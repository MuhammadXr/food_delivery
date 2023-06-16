package xr.muhammad.hammertesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import xr.muhammad.hammertesttask.ui.navigation.Screen
import xr.muhammad.hammertesttask.ui.navigation.SetupGraph
import xr.muhammad.hammertesttask.ui.theme.HammerTestTaskTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HammerTestTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    navController = rememberNavController()
                    Scaffold(
                        Modifier.safeContentPadding(),
                        bottomBar = {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                val items = listOf(
                                    Screen.Home,
                                    Screen.Cart,
                                    Screen.Profile
                                )
                                items.forEach { screen ->
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                imageVector = screen.icon,
                                                contentDescription = null
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        label = { Text(text = screen.route) },
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }

                        }
                    ) { innerPadding ->
//                        NavHost(
//                            navController = navController,
//                            startDestination = Screen.Home.route,
//                            Modifier.padding(innerPadding)
//                        ) {
//                            composable(Screen.Home.route) {
//                                HomeScreen(navController = navController).Content()
//                            }
//                            composable(Screen.Cart.route) {
//                                HomeScreen(navController = navController).Content()
//                            }
//                            composable(Screen.Profile.route) {
//                                HomeScreen(navController = navController).Content()
//                            }
//
//                        }
                        SetupGraph(navController = navController, padding = innerPadding)
                    }

                }
            }
        }
    }
}
