package xr.muhammad.hammertesttask.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import xr.muhammad.hammertesttask.api.local.FoodEntities
import xr.muhammad.hammertesttask.api.models.Category
import xr.muhammad.hammertesttask.api.models.Product

class FoodTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun foodToString(category: List<Category>): String {
        return gson.toJson(category)
    }

    @TypeConverter
    fun stringToFood(category: String): List<Category> {
        val objectType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(category, objectType)
    }

}

class CartTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun cartToString(product: Product): String {
        return gson.toJson(product)
    }

    @TypeConverter
    fun stringToCart(category: String): Product {
        val objectType = object : TypeToken<Product>() {}.type
        return gson.fromJson(category, objectType)
    }

}