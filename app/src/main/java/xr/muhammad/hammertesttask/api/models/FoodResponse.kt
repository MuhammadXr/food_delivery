// To parse the JSON, install Klaxon and do:
//
//   val foodResponse = FoodResponse.fromJson(jsonString)

package xr.muhammad.hammertesttask.api.models

import com.beust.klaxon.*

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
    val pageProps: PageProps? = null,

    @Json(name = "__N_SSP")
    val nSSP: Boolean? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<FoodResponse>(json)
    }
}

data class PageProps (
    val banners: List<Banner>? = null,
    val categories: List<Category>? = null,
    val popups: Any? = null,

    @Json(name = "__lang")
    val lang: String? = null,

    @Json(name = "__namespaces")
    val namespaces: Namespaces? = null
)

data class Banner (
    val id: String? = null,
    val title: Title? = null,
    val position: String? = null,
    val image: String? = null,
    val url: String? = null,
    val active: Boolean? = null,

    @Json(name = "created_at")
    val createdAt: String? = null,

    @Json(name = "updated_at")
    val updatedAt: String? = null,

    @Json(name = "shipper_id")
    val shipperID: String? = null,

    val about: String? = null
)

data class Title (
    val uz: String? = null,
    val ru: String? = null,
    val en: String? = null
)

data class Category (
    val id: String? = null,
    val slug: String? = null,

    @Json(name = "parent_id")
    val parentID: String? = null,

    val image: String? = null,
    val description: Title? = null,
    val title: Title? = null,

    @Json(name = "order_no")
    val orderNo: String? = null,

    val active: Boolean? = null,
    val products: List<Product>? = null,

    @Json(name = "child_categories")
    val childCategories: Any? = null
)

data class Product (
    val id: String? = null,

    @Json(name = "out_price")
    val outPrice: Long? = null,

    val currency: Currency? = null,
    val string: String? = null,
    val type: Type? = null,
    val categories: List<String>? = null,

    @Json(name = "brand_id")
    val brandID: String? = null,

    @Json(name = "rate_id")
    val rateID: String? = null,

    val image: String? = null,

    @Json(name = "active_in_menu")
    val activeInMenu: Boolean? = null,

    @Json(name = "has_modifier")
    val hasModifier: Boolean? = null,

    @Json(name = "from_time")
    val fromTime: String? = null,

    @Json(name = "to_time")
    val toTime: String? = null,

    @Json(name = "off_always")
    val offAlways: Boolean? = null,

    val gallery: Any? = null,
    val title: Title? = null,
    val description: Title? = null,
    val active: Boolean? = null,

    @Json(name = "iiko_id")
    val iikoID: String? = null,

    @Json(name = "jowi_id")
    val jowiID: String? = null,

    val discounts: Any? = null
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

data class Namespaces (
    val common: Map<String, String>? = null
)
