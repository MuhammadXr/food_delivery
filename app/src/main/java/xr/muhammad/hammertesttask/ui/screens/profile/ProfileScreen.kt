package xr.muhammad.hammertesttask.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen

class ProfileScreen(navController: NavHostController): AndroidScreen() {
    @Composable
    override fun Content() {
        ProfileScreenContent()
    }

}

@Composable
fun ProfileScreenContent(){
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Green)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "SAlom", color = Color.Black)
        }
    }
}
