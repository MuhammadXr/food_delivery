package xr.muhammad.hammertesttask.ui.screens.profile

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.layoutId
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import xr.muhammad.hammertesttask.R
import xr.muhammad.hammertesttask.profile.model.Profile
import xr.muhammad.hammertesttask.profile.utils.Constants.PROFILE_PIC_ID
import xr.muhammad.hammertesttask.profile.utils.Constants.TYPE
import xr.muhammad.hammertesttask.profile.utils.Constants.USERNAME
import xr.muhammad.hammertesttask.ui.screens.AndroidScreen


class ProfileScreen(navController: NavHostController, private val paddingValues: PaddingValues) : AndroidScreen() {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
            ProfileScreenContent()
        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreenContent() {
    MaterialTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFFFFFFF),
                            Color(0xFFFF9800)
                        )
                    )
                ),
            color = Color.Transparent
        ) {
            val progress by remember {
                mutableStateOf(0f)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(PaddingValues(top = 20.dp))

                ) {
                    ProfileHeader(
                        "${profile.firstName} ${profile.lastName}",
                        profile.avatarUrl,
                        "Android Flutter Developer",
                        progress
                    )
                }
                val bottomSheetState = rememberBottomSheetScaffoldState()
                BottomSheetScaffold(
                    scaffoldState = bottomSheetState,
                    sheetContent = {
                        MoreInfoCard()
                    },
                    sheetBackgroundColor = Color.Transparent,
                    sheetElevation = (-1).dp,
                    backgroundColor = Color.Transparent,
                    sheetPeekHeight = 100.dp

                ) {
                    Text(text = "")
                }
            }
        }
    }
}


private val profile = Profile()
fun checkAdmin(): String {
    return if (profile.isAdmin) "Admin" else "User"
}

@Composable
fun ProfileHeader(name: String, avatar: Int, type: String, progress: Float) {
    val context = LocalContext.current
    Image(
        painter = painterResource(avatar),
        contentDescription = "Contact profile picture",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(200.dp)
            .clip(CircleShape)
            .layoutId(PROFILE_PIC_ID)
    )
    Text(
        text = name,
        modifier = Modifier
            .padding(PaddingValues(top = 20.dp, bottom = 10.dp))
            .layoutId(USERNAME),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    Text(text = type, modifier = Modifier.layoutId(TYPE))
}




@Composable
fun MoreInfoCard() {

    val emailIntent = Intent(
        Intent.ACTION_SENDTO, Uri.fromParts(
            "mailto", "xolxujayevv@gmail.com", null
        )
    )
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Job offer")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)
            .height(400.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_drop_up),
                contentDescription = null
            )
        }
        Text(
            stringResource(R.string.bottomsheet_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            stringResource(R.string.bottomsheet_subtitle),
            color = Gray,
            modifier = Modifier.padding(bottom = 50.dp)
        )
        val context = LocalContext.current
        Box(modifier = Modifier.clickable {
            startActivity(
                context, Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://t.me/MuhammadXolxujayev"
                    )
                ), null
            )
        }){ BottomSheetCell("My Telegram", "@MuhammadXolxujayev") }
        Box(modifier = Modifier.clickable {
            startActivity(
                context, Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://t.me/muhammadxr_android"
                    )
                ), null
            )
        }){ BottomSheetCell("My Portfolio", "@muhammadxr_android") }
        Box(modifier = Modifier.clickable {
            startActivity(
                context, Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://www.linkedin.com/in/ruzimukhammad/"
                    )
                ), null
            )
        }){ BottomSheetCell("My LinkedIn", "linkedin.com/in/ruzimukhammad/") }
        Box(modifier = Modifier.clickable {
            startActivity(
                context, Intent(
                    Intent.ACTION_VIEW, Uri.parse(
                        "https://github.com/MuhammadXr"
                    )
                ), null
            )
        }){ BottomSheetCell("My GitHub", "github.com/MuhammadXr") }
        Box(modifier = Modifier.clickable {
            context.startActivity(Intent.createChooser(emailIntent, null))
        }){ BottomSheetCell(stringResource(R.string.email_title), profile.email) }

        Box(modifier = Modifier.clickable { }){ BottomSheetCell(stringResource(R.string.phone_title), profile.telephone) }
        Box(modifier = Modifier.clickable { }){ BottomSheetCell(stringResource(R.string.gender_title), profile.gender) }
    }
}

@Composable
private fun BottomSheetCell(title: String, value: String) {
    Row(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            "$title : ",
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        Text(value, color = Blue)
    }
    Divider(thickness = 1.dp, color = Gray)
    Spacer(modifier = Modifier.size(20.dp))
}

@ExperimentalMaterialApi
@Composable
private fun BottomSheet() {

}
