package xr.muhammad.hammertesttask.api.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import xr.muhammad.hammertesttask.api.models.Category

@Entity(tableName = "food_table")
class FoodEntities(
    var category: List<Category>
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}