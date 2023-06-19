package xr.muhammad.hammertesttask.ui.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmadhamwi.tabsync_compose.lazyListTabSync
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import xr.muhammad.hammertesttask.R
import xr.muhammad.hammertesttask.api.models.Banner
import xr.muhammad.hammertesttask.api.models.GeoObject
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen
import xr.muhammad.hammertesttask.viewmodel.HomeViewModelImpl


class HomeScreen(navController: NavHostController, private val padding: PaddingValues) :
    AndroidScreen() {
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
    val lazyListState = rememberLazyListState()
    val locationSelectDialog = remember { mutableStateOf(false) }
    if (categories.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val (selectedTabIndex, setSelectedTabIndex, syncedListState) =
            lazyListTabSync(
                syncedIndices = (0..(categories.size + 2)).toList(),
                lazyListState = lazyListState,
            )


        val isScrolled = remember {
            mutableStateOf(false)
        }
        val firstItemTranslationY = remember {
            derivedStateOf {
                when {
                    syncedListState.layoutInfo.visibleItemsInfo.isNotEmpty() &&
                            syncedListState.firstVisibleItemIndex == 0 -> {
                        isScrolled.value = true;
                        syncedListState.firstVisibleItemScrollOffset * .6f
                    }

                    else -> 0f
                }
            }
        }

        val visibility by remember {
            derivedStateOf {
                when {
                    syncedListState.layoutInfo.visibleItemsInfo.isNotEmpty() && syncedListState.firstVisibleItemIndex == 0 -> {
                        val imageSize = syncedListState.layoutInfo.visibleItemsInfo[0].size
                        val scrollOffset = syncedListState.firstVisibleItemScrollOffset
                        scrollOffset / imageSize.toFloat()
                    }

                    else -> 1f
                }
            }
        }
        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp, end = 18.dp, top = 42.dp)
                    .background(color = Color.White),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                TopAppBar(
                    onLocationClick = {
                        locationSelectDialog.value = locationSelectDialog.value.not()
                    },
                    locationName = viewModel.locationState.value.name?:"Select Location"
                    )

                ScrollableTabRow(
                    selectedTabIndex - 2,
                    modifier = Modifier
                        .background(color = Color.White)
                        .graphicsLayer {
                            alpha = if (syncedListState.firstVisibleItemIndex >= 2) 1f else 0f
                        },
                    indicator = {},
                    divider = {},
                ) {
                    categories.forEachIndexed { index, category ->
                        Tab(
                            modifier = Modifier
                                .background(color = Color.White),
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White,
                            selected = index + 2 == selectedTabIndex,
                            onClick = { setSelectedTabIndex(index + 2) },
                            content = {
                                Card(
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp, vertical = 6.dp),
                                    elevation = 5.dp,
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .background(
                                                color = if (index + 2 == selectedTabIndex) Color(
                                                    0xFFFFD073
                                                ) else Color.White
                                            )
                                            .padding(horizontal = 12.dp, vertical = 6.dp),
                                        text = category.title?.ru ?: index.toString(),
                                        color =
                                        if (index + 2 == selectedTabIndex) Color(0xFF5A2E00)
                                        else Color.Gray,
                                        fontWeight = FontWeight.W500
                                    )
                                }
                            }
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier,
                    state = syncedListState,
                    content = {

                        item {
                            BannerSlider(
                                banners = banners,
                                modifier = Modifier
                                    .graphicsLayer {

                                        alpha = 1f - visibility
                                        translationY = firstItemTranslationY.value

                                    },
                            )
                        }
                        item {
                            ScrollableTabRow(
                                selectedTabIndex - 2,
                                modifier = Modifier
                                    .padding(top = 6.dp)
                                    .background(color = Color.White),
                                indicator = {},
                                divider = {},
                            ) {
                                categories.forEachIndexed { index, category ->
                                    Tab(
                                        modifier = Modifier
                                            .background(color = Color.White),
                                        selectedContentColor = Color.White,
                                        unselectedContentColor = Color.White,
                                        selected = index + 2 == selectedTabIndex,
                                        onClick = { setSelectedTabIndex(index + 2) },
                                        content = {
                                            Card(
                                                modifier = Modifier
                                                    .padding(horizontal = 16.dp, vertical = 6.dp),
                                                elevation = 5.dp,
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Text(
                                                    modifier = Modifier
                                                        .background(
                                                            color = if (index + 2 == selectedTabIndex) Color(
                                                                0xFFFFD073
                                                            ) else Color.White
                                                        )
                                                        .padding(
                                                            horizontal = 12.dp,
                                                            vertical = 6.dp
                                                        ),
                                                    text = category.title?.ru ?: index.toString(),
                                                    color =
                                                    if (index + 2 == selectedTabIndex) Color(
                                                        0xFF5A2E00
                                                    )
                                                    else Color.Gray,
                                                    fontWeight = FontWeight.W500
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        items(
                            items = categories,

                            ) {
                            it.title?.ru?.let { it1 ->
                                Text(
                                    modifier = Modifier.padding(vertical = 20.dp),
                                    text = it1,
                                )
                                Divider()
                                Box(modifier = Modifier.height(16.dp))
                            }
                            for (index in it.products.indices step 2) {
                                val product = it.products[index]
                                Row(
                                    modifier = Modifier
                                        .padding(vertical = 6.dp)
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
                                            val addedToCart = remember {
                                                mutableStateOf(false)
                                            }
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
                                                    .clickable {
                                                        viewModel.addToCart(product)
                                                        addedToCart.value = true
                                                    }
                                                    .padding(
                                                        horizontal = 12.dp,
                                                        vertical = 6.dp
                                                    ),
                                                text = if (addedToCart.value.not())
                                                "${product.outPrice} ${product.currency}" else "ADDED TO CART"
                                            )
                                        }
                                    }
                                }
                                Divider(thickness = 0.2.dp, color = Color.LightGray)
                            }
                        }
                    },
                )
            }
            if (locationSelectDialog.value) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                ) {
                    LocationSelectContent(viewModel = viewModel, onDismiss = {
                        locationSelectDialog.value = locationSelectDialog.value.not()
                    })
                }
            }
        }
    }
}

@SuppressLint("ResourceType")
@Composable
fun TopAppBar(
    onLocationClick: () -> Unit,
    locationName: String = "Select Location",
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onLocationClick)
                .padding(vertical = 6.dp, horizontal = 6.dp)
        ) {
            Text(text = locationName, fontWeight = FontWeight.W500, fontSize = 18.sp)
            Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = null)
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.qr_code),
            modifier = Modifier
                .size(28.dp)
                .clickable {

                },
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerSlider(
    banners: List<Banner>,
    modifier: Modifier
) {
    val state = rememberPagerState()
    HorizontalPager(
        pageCount = banners.size,
        pageSize = PageSize.Fixed(300.dp),
        pageSpacing = 24.dp,
        state = state
    ) {
        Card(
            modifier = modifier
                .width(300.dp)
                .height(130.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
        ) {
            Image(
                painter = painterResource(id = banners[it].imageResId), contentDescription = null,
                alpha = if (state.currentPage == it) 1f else .5f
            )
        }
    }

}

@Composable
fun LocationSelectContent(
    viewModel: HomeViewModelImpl,
    onDismiss: ()->Unit
) {

    val searchField = remember{ mutableStateOf("") }
    val currentLocation = remember { mutableStateOf(GeoObject(null,null,null,null,null)) }
    val queryLocations by viewModel.queryLocations.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 42.dp), verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp),
        ) {
            SearchBox(
                searchField = searchField,
                onValueChange = {
                    searchField.value = it
                    viewModel.getLocations(it)
                }
            )
            LazyColumn(
                modifier = Modifier.weight(10f),
                content = {
                items(items = queryLocations){
                    Row(modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()

                        .clip(shape = RoundedCornerShape(12.dp),)
                        .background(color = Color.LightGray)
                        .border(
                            width = 2.dp, shape = RoundedCornerShape(12.dp),
                            color = if (currentLocation.value == it.GeoObject) Color(0xFFFF9800) else Color.LightGray
                        )
                        .clickable {
                            if (it.GeoObject != null)
                                currentLocation.value = it.GeoObject
                        }
                        .padding(24.dp)
                        ,
                    ) {
                        Column(verticalArrangement = Arrangement.SpaceEvenly) {
                            Text(text = it.GeoObject?.name?:"")
                            Box(modifier = Modifier.height(12.dp))
                            Text(text = it.GeoObject?.description ?: "")
                        }
                    }
                }
            })
        }
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(42.dp)
                .padding(horizontal = 24.dp),
            enabled = currentLocation.value.name != null,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFFFF9800),
            ),
            onClick = {
                viewModel.setLocation(currentLocation.value)
                onDismiss.invoke()
            }) {
            Text(text = "Select")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(
    searchField: MutableState<String>,
    onValueChange: (String)->Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchField.value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(12.dp),
        label = { Text(text = "Search")},
        trailingIcon = {
            IconButton(onClick = {
                searchField.value = ""
            }) {
                Icon(imageVector = Icons.Filled.Close
                    , contentDescription = null)
            }
        },
        colors = androidx.compose.material3.TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = Color(
            0xFFFF9800
        )
        ),
    )
}