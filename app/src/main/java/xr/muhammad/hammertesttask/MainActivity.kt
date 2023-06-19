package xr.muhammad.hammertesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
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

            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(
                color = Color(0xFFE9E9E9)
            )
            systemUiController.setStatusBarColor(
                color = Color(0xFFFFCD82)
            )

            HammerTestTaskTheme (darkTheme = false){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    navController = rememberNavController()
                    Scaffold(
                        Modifier.safeContentPadding(),
                        bottomBar = {
                            BottomNavigation (
                                backgroundColor = Color(0xFFC9C9C9)
                                    ){
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                val items = listOf(
                                    Screen.Home,
                                    Screen.Cart,
                                    Screen.Profile
                                )
                                items.forEach { screen ->
                                    var selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                                    BottomNavigationItem(
                                        icon = {
                                            Icon(
                                                imageVector = screen.icon,
                                                contentDescription = null,
                                                tint = if (selected) Color(0xFFE27D00) else Color.Black
                                            )
                                        },
                                        selected = selected,
                                        label = {
                                            Text(
                                                text = screen.route,
                                                fontWeight = FontWeight.W600,
                                                color = if (selected) Color(0xFFE27D00) else Color.Black)
                                                },
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                    )
                                }
                            }

                        }
                    ) { innerPadding ->
                        SetupGraph(navController = navController, padding = innerPadding)
                    }

                }
            }
        }
    }
}
