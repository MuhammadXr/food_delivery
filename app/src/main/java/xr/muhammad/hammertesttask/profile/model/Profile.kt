package xr.muhammad.hammertesttask.profile.model

import xr.muhammad.hammertesttask.R

data class Profile(
    val isAdmin: Boolean = false,
    val firstName: String = "Ruzi Mukhammad",
    val lastName: String = "Khokhujayev",
    val email: String = "xolxujayevv@gmail.com",
    val telephone: String = "+998993391240",
    val gender: String = "Male",
    val avatarUrl: Int = R.drawable.profile,
)