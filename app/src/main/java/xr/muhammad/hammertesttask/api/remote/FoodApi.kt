package xr.muhammad.hammertesttask.api.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import xr.muhammad.hammertesttask.api.models.FoodResponse

interface FoodApi {
    @GET
    fun getAllData(): Response<FoodResponse>
}