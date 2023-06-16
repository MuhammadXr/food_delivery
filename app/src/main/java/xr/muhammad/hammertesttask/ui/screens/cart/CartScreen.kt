package xr.muhammad.hammertesttask.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen
import xr.muhammad.hammertesttask.ui.screens.home.viewmodel.HomeViewModel
import xr.muhammad.hammertesttask.viewmodel.HomeViewModelImpl
import javax.inject.Inject


class CartScreen(
    navController: NavHostController,
): AndroidScreen() {


    @Composable
    override fun Content() {

        CartScreenContent()

    }

}

@Composable
fun CartScreenContent(
    viewModel: HomeViewModelImpl = hiltViewModel()
){
    val categories by viewModel.allCategory.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Red)
            ,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "SAlom", color = Color.Black)
            LazyColumn(
                content = {
                items(categories){
                    it.title?.ru?.let { it1 -> Text(text = it1) }
                }
            })
        }
    }
}