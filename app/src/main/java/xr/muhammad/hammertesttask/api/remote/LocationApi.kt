package xr.muhammad.hammertesttask.api.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xr.muhammad.hammertesttask.api.models.LocationResponse

private val APIKEY = "650c4be4-ce15-446e-81ef-99525c200558"

interface LocationApi {

    @GET()
    suspend fun getLocations(
        @Query("apikey") apikey: String = APIKEY,
        @Query("geocode") search: String,
        @Query("results") result: Int = 5,
        @Query("lang") lang: String = "ru_RU",
        @Query("format") format: String = "json"
    ): Response<LocationResponse>

}