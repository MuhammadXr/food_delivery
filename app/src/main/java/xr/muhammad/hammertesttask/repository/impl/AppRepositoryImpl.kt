package xr.muhammad.hammertesttask.repository.impl

import kotlinx.coroutines.flow.Flow
import xr.muhammad.hammertesttask.api.local.CartEntity
import xr.muhammad.hammertesttask.api.local.FoodEntities
import xr.muhammad.hammertesttask.api.models.FoodResponse
import xr.muhammad.hammertesttask.api.models.LocationResponse
import xr.muhammad.hammertesttask.api.remote.FoodApi
import xr.muhammad.hammertesttask.api.remote.LocationApi
import xr.muhammad.hammertesttask.repository.AppRepository
import xr.muhammad.hammertesttask.room.CartDao
import xr.muhammad.hammertesttask.room.FoodDao
import javax.inject.Inject


class AppRepositoryImpl @Inject constructor(
    private val foodApi: FoodApi,
    private val locationApi: LocationApi,
    private val foodDao: FoodDao,
    private val cartDao: CartDao
) : AppRepository {



    override suspend fun getAllFoodData(): FoodResponse? {
        val response = foodApi.getAllData()
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    override suspend fun searchLocation(search: String): LocationResponse? {
        val response = locationApi.getLocations(search = search)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }

    override suspend fun insertFoodsToLocal(foodEntities: FoodEntities) {
        foodDao.insertFoods(foodEntity = foodEntities)
    }

    override fun readFoodsFromLocal(): Flow<FoodEntities> {
        return foodDao.readFoodDataBase()
    }

    override suspend fun insertCartToLocal(cartEntity: CartEntity) {
        cartDao.insertCart(cartEntity)
    }

    override fun readCartsFromLocal(): Flow<List<CartEntity>> {
        return cartDao.readCartDataBase()
    }

    override suspend fun deleteAllCarts() {
        cartDao.deleteAll()
    }
}