// To parse the JSON, install Klaxon and do:
//
//   val foodResponse = FoodResponse.fromJson(jsonString)

package xr.muhammad.hammertesttask.api.models

import com.beust.klaxon.*
import com.google.gson.annotations.SerializedName

private fun <T> Klaxon.convert(k: kotlin.reflect.KClass<*>, fromJson: (JsonValue) -> T, toJson: (T) -> String, isUnion: Boolean = false) =
    this.converter(object: Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any)        = toJson(value as T)
        override fun fromJson(jv: JsonValue)   = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) = cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Currency::class, { Currency.fromValue(it.string!!) }, { "\"${it.value}\"" })
    .convert(Type::class,     { Type.fromValue(it.string!!) },     { "\"${it.value}\"" })

data class FoodResponse (
    val categories: List<Category>? = null,
    val count: String? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<FoodResponse>(json)
    }
}

data class Category (

    @SerializedName("id"               ) var id              : String?             = null,
    @SerializedName("slug"             ) var slug            : String?             = null,
    @SerializedName("parent_id"        ) var parentId        : String?             = null,
    @SerializedName("image"            ) var image           : String?             = null,
    @SerializedName("description"      ) var description     : Description?        = Description(),
    @SerializedName("title"            ) var title           : Title?              = Title(),
    @SerializedName("order_no"         ) var orderNo         : String?             = null,
    @SerializedName("active"           ) var active          : Boolean?            = null,
    @SerializedName("products"         ) var products        : List<Product> = arrayListOf(),
    @SerializedName("child_categories" ) var childCategories : String?             = null

)

data class Description (
    val uz: String? = null,
    val ru: String? = null,
    val en: String? = null
)

data class Product (

    @SerializedName("id"             ) var id           : String?           = null,
    @SerializedName("out_price"      ) var outPrice     : Int?              = null,
    @SerializedName("currency"       ) var currency     : String?           = null,
    @SerializedName("string"         ) var string       : String?           = null,
    @SerializedName("type"           ) var type         : String?           = null,
    @SerializedName("categories"     ) var categories   : ArrayList<String> = arrayListOf(),
    @SerializedName("brand_id"       ) var brandId      : String?           = null,
    @SerializedName("rate_id"        ) var rateId       : String?           = null,
    @SerializedName("image"          ) var image        : String?           = null,
    @SerializedName("active_in_menu" ) var activeInMenu : Boolean?          = null,
    @SerializedName("has_modifier"   ) var hasModifier  : Boolean?          = null,
    @SerializedName("from_time"      ) var fromTime     : String?           = null,
    @SerializedName("to_time"        ) var toTime       : String?           = null,
    @SerializedName("off_always"     ) var offAlways    : Boolean?          = null,
    @SerializedName("gallery"        ) var gallery      : String?           = null,
    @SerializedName("title"          ) var title        : Title?            = Title(),
    @SerializedName("description"    ) var description  : Description?      = Description(),
    @SerializedName("active"         ) var active       : Boolean?          = null,
    @SerializedName("iiko_id"        ) var iikoId       : String?           = null,
    @SerializedName("jowi_id"        ) var jowiId       : String?           = null,
    @SerializedName("discounts"      ) var discounts    : String?           = null

)

data class Title (

    @SerializedName("uz" ) var uz : String? = null,
    @SerializedName("ru" ) var ru : String? = null,
    @SerializedName("en" ) var en : String? = null

)

enum class Currency(val value: String) {
    Uzs("UZS");

    companion object {
        public fun fromValue(value: String): Currency = when (value) {
            "UZS" -> Uzs
            else  -> throw IllegalArgumentException()
        }
    }
}

enum class Type(val value: String) {
    Origin("origin"),
    Simple("simple");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "origin" -> Origin
            "simple" -> Simple
            else     -> throw IllegalArgumentException()
        }
    }
}
