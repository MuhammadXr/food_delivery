package xr.muhammad.hammertesttask.repository

import xr.muhammad.hammertesttask.api.models.FoodResponse
import xr.muhammad.hammertesttask.api.models.LocationResponse

interface AppRepository {

    suspend fun getAllFoodData(): FoodResponse?

    suspend fun searchLocation(search: String): LocationResponse?

}