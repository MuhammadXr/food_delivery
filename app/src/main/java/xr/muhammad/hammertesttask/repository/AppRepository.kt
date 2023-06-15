package xr.muhammad.hammertesttask.repository

import xr.muhammad.hammertesttask.api.models.FoodResponse
import xr.muhammad.hammertesttask.api.models.LocationResponse

interface AppRepository {

    fun getAllFoodData(): FoodResponse?

    fun searchLocation(search: String): LocationResponse?

}