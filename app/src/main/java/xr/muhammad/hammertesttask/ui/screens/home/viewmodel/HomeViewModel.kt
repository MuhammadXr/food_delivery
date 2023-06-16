package xr.muhammad.hammertesttask.ui.screens.home.viewmodel

import kotlinx.coroutines.flow.StateFlow
import xr.muhammad.hammertesttask.api.models.Banner
import xr.muhammad.hammertesttask.api.models.Category
import xr.muhammad.hammertesttask.api.models.FeatureMember
import xr.muhammad.hammertesttask.api.models.GeoObject
import xr.muhammad.hammertesttask.api.models.LocationResponse

interface HomeViewModel {
    val locationState: StateFlow<GeoObject>
    val queryLocations: StateFlow<List<FeatureMember>>
    val allBanners: StateFlow<List<Banner>>
    val allCategory: StateFlow<List<Category>>

    val locationError: StateFlow<Boolean>
    val foodError: StateFlow<Boolean>

    fun getLocations(search: String)

    fun getAllFoodData()

    fun setLocation(geoObject: GeoObject)

}