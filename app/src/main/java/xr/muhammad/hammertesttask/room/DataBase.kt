package xr.muhammad.hammertesttask.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import xr.muhammad.hammertesttask.api.local.CartEntity
import xr.muhammad.hammertesttask.api.local.FoodEntities

@Database(
    entities = [FoodEntities::class, CartEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(FoodTypeConverter::class, CartTypeConverter::class)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun cartDao(): CartDao

}