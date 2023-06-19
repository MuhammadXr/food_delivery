package xr.muhammad.hammertesttask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xr.muhammad.hammertesttask.api.local.FoodEntities

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foodEntity: FoodEntities)

    @Query("SELECT * FROM food_table ORDER BY id ASC")
    fun readFoodDataBase(): Flow<FoodEntities>

}