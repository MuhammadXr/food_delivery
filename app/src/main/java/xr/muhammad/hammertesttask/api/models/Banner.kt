package xr.muhammad.hammertesttask.api.models

import xr.muhammad.hammertesttask.R

data class Banner (
    val name: String,
    val imageResId: Int
        )

public fun getBanners(): List<Banner>{
    return listOf<Banner>(
        Banner(name = "Maxi Popular", imageResId = R.drawable.maxi_popular),
        Banner(name = "Maxi Traditsiya", imageResId = R.drawable.maxi_traditsiya),
        Banner(name = "Maxi Trend", imageResId = R.drawable.maxi_trend),
    );
}