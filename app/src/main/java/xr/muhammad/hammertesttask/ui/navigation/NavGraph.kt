package xr.muhammad.hammertesttask.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import xr.muhammad.hammertesttask.ui.screens.cart.CartScreen
import xr.muhammad.hammertesttask.ui.screens.home.HomeScreen
import xr.muhammad.hammertesttask.ui.screens.profile.ProfileScreen

@Composable
fun SetupGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route

    ) {
        composable(
            route = Screen.Home.route,
        ) {
            HomeScreen(navController, padding).Content()
        }
        composable(
            route = Screen.Profile.route
        ) {
            ProfileScreen(navController).Content()
        }
        composable(
            route = Screen.Cart.route
        ) {
            CartScreen(navController).Content()
        }
    }
}