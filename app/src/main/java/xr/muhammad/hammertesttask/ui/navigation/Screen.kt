package xr.muhammad.hammertesttask.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon:ImageVector){
    object Cart: Screen(route = "cart", icon = Icons.Filled.ShoppingCart)
    object Home: Screen(route = "home", icon = Icons.Filled.Home)
    object Profile: Screen(route = "profile", icon = Icons.Filled.Person)
}
