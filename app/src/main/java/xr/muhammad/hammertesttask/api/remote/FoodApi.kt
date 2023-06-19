package xr.muhammad.hammertesttask.api.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import xr.muhammad.hammertesttask.api.models.FoodResponse


private const val API_KEY:String =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2ODgzNDY3NTMsImlhdCI6MTY4NzA1MDc1MywiaXNzIjoidXNlciIsInNoaXBwZXJfaWQiOiJkMGQwYzdjOS1lMDQ3LTRhZDgtOTY3NC1hOTRhMjdlM2RhNzMiLCJzdWIiOiIyZjk1NmM1Ni03YWQ3LTQzZTUtOTMzOC0zMTE2Y2RmOTliYmMiLCJ1c2VyX3R5cGVfaWQiOiI5YjMxMjg2ZC1kYzIxLTQ1NzItYjIwYy05YjZjYjdkMjlkODkifQ.lNcrj57qDh2w214pcJvysolnZFysJyAL95e5IJd3Zjc"
interface FoodApi {
    @GET("v2/category-with-products")
    suspend
    fun getAllData(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 30,
        @Query("menu_id") menuId: String = "",
        @Query("order_source") orderSource: String = "website",
        @Query("only_delivery") onlyDelivery: Boolean = true,
        @Query("only_self_pickup") onlySelfPickup: Boolean = false,
        @Query("branch_id") branchId: String = "undefined",
        @Query("client_id") clientId: String = "2f956c56-7ad7-43e5-9338-3116cdf99bbc",
        @Query("with_discounts") withDiscounts: Boolean = true,
        @Header("Authorization") authorization: String = API_KEY,
    ):  Response<FoodResponse>
}