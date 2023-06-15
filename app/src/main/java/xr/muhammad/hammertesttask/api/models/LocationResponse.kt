// To parse the JSON, install Klaxon and do:
//
//   val locationResponse = LocationResponse.fromJson(jsonString)

package xr.muhammad.hammertesttask.api.models

import com.beust.klaxon.*

private fun <T> Klaxon.convert(
    k: kotlin.reflect.KClass<*>,
    fromJson: (JsonValue) -> T,
    toJson: (T) -> String,
    isUnion: Boolean = false
) =
    this.converter(object : Converter {
        @Suppress("UNCHECKED_CAST")
        override fun toJson(value: Any) = toJson(value as T)
        override fun fromJson(jv: JsonValue) = fromJson(jv) as Any
        override fun canConvert(cls: Class<*>) =
            cls == k.java || (isUnion && cls.superclass == k.java)
    })

private val klaxon = Klaxon()
    .convert(Kind::class, { Kind.fromValue(it.string!!) }, { "\"${it.value}\"" })

data class LocationResponse(
    val response: Response? = null
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<LocationResponse>(json)
    }
}

data class Response(
    @Json(name = "GeoObjectCollection")
    val geoObjectCollection: GeoObjectCollection? = null
)

data class GeoObjectCollection(
    val metaDataProperty: GeoObjectCollectionMetaDataProperty? = null,
    val featureMember: List<FeatureMember>? = null
)

data class FeatureMember(
    @Json(name = "GeoObject")
    val geoObject: GeoObject? = null
)

data class GeoObject(
    val metaDataProperty: GeoObjectMetaDataProperty? = null,
    val name: String? = null,
    val description: String? = null,
    val boundedBy: BoundedBy? = null,

    @Json(name = "Point")
    val point: Point? = null
)

data class BoundedBy(
    @Json(name = "Envelope")
    val envelope: Envelope? = null
)

data class Envelope(
    val lowerCorner: String? = null,
    val upperCorner: String? = null
)

data class GeoObjectMetaDataProperty(
    @Json(name = "GeocoderMetaData")
    val geocoderMetaData: GeocoderMetaData? = null
)

data class GeocoderMetaData(
    val precision: String? = null,
    val text: String? = null,
    val kind: Kind? = null,

    @Json(name = "Address")
    val address: Address? = null,

    @Json(name = "AddressDetails")
    val addressDetails: AddressDetails? = null
)

data class Address(
    @Json(name = "country_code")
    val countryCode: String? = null,

    val formatted: String? = null,

    @Json(name = "Components")
    val components: List<Component>? = null
)

data class Component(
    val kind: Kind? = null,
    val name: String? = null
)

enum class Kind(val value: String) {
    Area("area"),
    Country("country"),
    Locality("locality"),
    Province("province");

    companion object {
        public fun fromValue(value: String): Kind = when (value) {
            "area" -> Area
            "country" -> Country
            "locality" -> Locality
            "province" -> Province
            else -> throw IllegalArgumentException()
        }
    }
}

data class AddressDetails(
    @Json(name = "Country")
    val country: Country? = null
)

data class Country(
    @Json(name = "AddressLine")
    val addressLine: String? = null,

    @Json(name = "CountryNameCode")
    val countryNameCode: String? = null,

    @Json(name = "CountryName")
    val countryName: String? = null,

    @Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeArea? = null
)

data class AdministrativeArea(
    @Json(name = "AdministrativeAreaName")
    val administrativeAreaName: String? = null,

    @Json(name = "SubAdministrativeArea")
    val subAdministrativeArea: SubAdministrativeArea? = null,

    @Json(name = "Locality")
    val locality: Locality? = null
)

data class Locality(
    @Json(name = "LocalityName")
    val localityName: String? = null
)

data class SubAdministrativeArea(
    @Json(name = "SubAdministrativeAreaName")
    val subAdministrativeAreaName: String? = null,

    @Json(name = "Locality")
    val locality: Locality? = null
)

data class Point(
    val pos: String? = null
)

data class GeoObjectCollectionMetaDataProperty(
    @Json(name = "GeocoderResponseMetaData")
    val geocoderResponseMetaData: GeocoderResponseMetaData? = null
)

data class GeocoderResponseMetaData(
    val request: String? = null,
    val results: String? = null,
    val found: String? = null
)
