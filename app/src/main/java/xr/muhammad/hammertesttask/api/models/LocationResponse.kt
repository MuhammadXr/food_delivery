package xr.muhammad.hammertesttask.api.models

data class LocationResponse(
    val response: Response?
)

data class Response(
    val GeoObjectCollection: GeoObjectCollection?
)

data class GeoObjectCollection(
    val featureMember: List<FeatureMember>?,
    val metaDataProperty: MetaDataPropertyX?
)

data class FeatureMember(
    val GeoObject: GeoObject?
)

data class MetaDataPropertyX(
    val GeocoderResponseMetaData: GeocoderResponseMetaData?
)

data class GeoObject(
    val Point: Point?,
    val boundedBy: BoundedBy?,
    val description: String?,
    val metaDataProperty: MetaDataProperty?,
    val name: String?
)

data class Point(
    val pos: String?
)

data class BoundedBy(
    val Envelope: Envelope?
)

data class MetaDataProperty(
    val GeocoderMetaData: GeocoderMetaData?
)

data class Envelope(
    val lowerCorner: String?,
    val upperCorner: String?
)

data class GeocoderMetaData(
    val Address: Address?,
    val AddressDetails: AddressDetails?,
    val kind: String?,
    val precision: String?,
    val text: String?
)

data class Address(
    val Components: List<Component?>?,
    val country_code: String?,
    val formatted: String?
)

data class AddressDetails(
    val Country: Country?
)

data class Component(
    val kind: String?,
    val name: String?
)

data class Country(
    val AddressLine: String?,
    val AdministrativeArea: AdministrativeArea?,
    val Country: CountryX?,
    val CountryName: String?,
    val CountryNameCode: String?,
    val Locality: LocalityXXX?,
    val Thoroughfare: ThoroughfareX?
)

data class AdministrativeArea(
    val AdministrativeAreaName: String?,
    val Locality: Locality?,
    val SubAdministrativeArea: SubAdministrativeArea?
)

data class CountryX(
    val Locality: LocalityXX?
)

data class LocalityXXX(
    val LocalityName: String?,
    val Premise: Premise?
)

data class ThoroughfareX(
    val ThoroughfareName: String?
)

data class Locality(
    val DependentLocality: DependentLocality?,
    val LocalityName: String?,
    val Thoroughfare: ThoroughfareX?
)

data class SubAdministrativeArea(
    val Locality: LocalityXXX?,
    val SubAdministrativeAreaName: String?
)

data class DependentLocality(
    val DependentLocalityName: String?,
    val Premise: Premise?
)

data class Premise(
    val PremiseName: String?
)

class LocalityXX

data class GeocoderResponseMetaData(
    val boundedBy: BoundedByX?,
    val found: String?,
    val request: String?,
    val results: String?
)

data class BoundedByX(
    val Envelope: Envelope?
)