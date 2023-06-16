package xr.muhammad.hammertesttask.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import xr.muhammad.hammertesttask.R
import xr.muhammad.hammertesttask.api.models.Banner
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen
import xr.muhammad.hammertesttask.viewmodel.HomeViewModelImpl


class HomeScreen(navController: NavHostController, private val padding: PaddingValues) : AndroidScreen() {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.padding(paddingValues = padding)) {
            HomeScreenContent()
        }

    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModelImpl = hiltViewModel()
) {
    val categories by viewModel.allCategory.collectAsState()
    val banners by viewModel.allBanners.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 18.dp, end = 18.dp, top = 42.dp)
                .background(color = Color.White),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            TopAppBar()
            BannerSlider(banners = banners)
            Text(text = "SAlom", color = Color.Black)
            LazyColumn(
                content = {
                    items(categories) {
                        it.title?.ru?.let { it1 -> Text(text = it1) }
                    }
                })
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row {
            Text(text = "Москва", fontWeight = FontWeight.W500, fontSize = 18.sp)
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.qr_code),
                modifier = Modifier.size(28.dp),
                contentDescription = null
            )

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSlider(
    banners: List<Banner>
){
    val state = rememberPagerState()
    HorizontalPager(
        pageCount = banners.size,
        pageSize = PageSize.Fixed(300.dp),
        pageSpacing = 24.dp,
        state = state
    ) {
        Card(modifier = Modifier
            .width(300.dp)
            .height(130.dp)
            .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            GlideImage(
                imageModel = { banners[it].image },
                imageOptions = ImageOptions(alpha = if (state.currentPage == it) 1f else .5f)
            )
        }
    }

}