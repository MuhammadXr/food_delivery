package xr.muhammad.hammertesttask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xr.muhammad.hammertesttask.api.local.CartEntity

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartEntity: CartEntity)

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    fun readCartDataBase(): Flow<List<CartEntity>>

    @Query("DELETE FROM cart_table")
    suspend fun deleteAll()
}
