package xr.muhammad.hammertesttask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import xr.muhammad.hammertesttask.ui.screens.archive.ArchiveScreen
import xr.muhammad.hammertesttask.ui.screens.home.HomeScreen
import xr.muhammad.hammertesttask.ui.screens.profile.ProfileScreen

@Composable
fun SetupGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route

    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController).Content()
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(navController).Content()
        }
        composable(
            route = Screen.Archive.route
        ) {
            ArchiveScreen(navController).Content()
        }
    }
}