package xr.muhammad.hammertesttask.repository.impl

import xr.muhammad.hammertesttask.api.models.FoodResponse
import xr.muhammad.hammertesttask.api.models.LocationResponse
import xr.muhammad.hammertesttask.api.remote.FoodApi
import xr.muhammad.hammertesttask.api.remote.LocationApi
import xr.muhammad.hammertesttask.repository.AppRepository
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    val foodApi: FoodApi,
    val locationApi: LocationApi
) : AppRepository {



    override fun getAllFoodData(): FoodResponse? {
        val response = foodApi.getAllData()
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    override fun searchLocation(search: String): LocationResponse? {
        val response = locationApi.getLocations(search)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}