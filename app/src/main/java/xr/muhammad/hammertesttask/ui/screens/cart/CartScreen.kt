package xr.muhammad.hammertesttask.ui.screens.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen
import xr.muhammad.hammertesttask.ui.screens.home.HomeScreenContent
import xr.muhammad.hammertesttask.ui.screens.home.viewmodel.HomeViewModel
import xr.muhammad.hammertesttask.viewmodel.CartViewModelImpl
import xr.muhammad.hammertesttask.viewmodel.HomeViewModelImpl
import javax.inject.Inject


class CartScreen(
    navController: NavHostController,
    private val padding: PaddingValues
): AndroidScreen() {


    @Composable
    override fun Content() {
        Box(modifier = Modifier.padding(paddingValues = padding)) {
            CartScreenContent()
        }
    }

}

@Composable
fun CartScreenContent(
    viewModel: CartViewModelImpl = hiltViewModel()
){
    val carts by viewModel.allCarts.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
            ,
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Cart", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            if (carts.isEmpty()){
                Text(text = "Empty Cart")
                Box(modifier = Modifier.height(12.dp))
            }else {
                Box(modifier = Modifier.height(24.dp))
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    content = {
                        items(
                            items = carts,

                            ) {
                            val product = it.products
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                            {
                                GlideImage(
                                    imageModel = {
                                        "https://cdn.delever.uz/delever/${product.image}"
                                    },
                                    modifier = Modifier.size(135.dp),
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.FillWidth
                                    )
                                )
                                Column(verticalArrangement = Arrangement.SpaceBetween) {
                                    Text(
                                        text = product.title?.ru ?: "",
                                        fontWeight = FontWeight.Bold,
                                    )
                                    Text(
                                        text = product.description?.ru ?: "",
                                        fontSize = 12.sp
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(
                                            modifier =
                                            Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = Color(
                                                        0xFFFFC107
                                                    ),
                                                    shape = RoundedCornerShape(6.dp)
                                                )
                                                .background(color = Color(0xFFFFF7E0))
                                                .padding(
                                                    horizontal = 12.dp,
                                                    vertical = 6.dp
                                                ),
                                            text = "${product.outPrice} ${product.currency}"
                                        )
                                    }
                                }
                            }
                        }
                    })

                Button(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .width(200.dp)
                        .height(42.dp)
                        .padding(horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFF9800),
                    ),
                    onClick = {
                        viewModel.orderAllCarts()
                    }) {
                    Text(text = "Checkout")
                }
            }
        }

    }
}