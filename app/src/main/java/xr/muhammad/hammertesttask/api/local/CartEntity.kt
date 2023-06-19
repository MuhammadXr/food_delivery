package xr.muhammad.hammertesttask.api.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import xr.muhammad.hammertesttask.api.models.Product

@Entity(tableName = "cart_table")
class CartEntity(
    var products: Product
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}