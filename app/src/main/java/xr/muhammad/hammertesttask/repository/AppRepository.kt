package xr.muhammad.hammertesttask.repository

import kotlinx.coroutines.flow.Flow
import xr.muhammad.hammertesttask.api.local.CartEntity
import xr.muhammad.hammertesttask.api.local.FoodEntities
import xr.muhammad.hammertesttask.api.models.FoodResponse
import xr.muhammad.hammertesttask.api.models.LocationResponse

interface AppRepository {

    suspend fun getAllFoodData(): FoodResponse?

    suspend fun searchLocation(search: String): LocationResponse?

    suspend fun insertFoodsToLocal(foodEntities: FoodEntities)

    fun readFoodsFromLocal(): Flow<FoodEntities>

    suspend fun insertCartToLocal(cartEntity: CartEntity)

    fun readCartsFromLocal(): Flow<List<CartEntity>>

    suspend fun deleteAllCarts()

}