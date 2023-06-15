package xr.muhammad.hammertesttask.ui.navigation

sealed class Screen(val route: String){
    object Archive: Screen(route = "archive")
    object Home: Screen(route = "home")
    object Profile: Screen(route = "profile")
}
