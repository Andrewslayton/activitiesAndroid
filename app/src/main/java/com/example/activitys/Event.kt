package com.example.activitys
import com.google.gson.annotations.SerializedName


data class EventsResponse(
    @SerializedName("_embedded") val embedded: EmbeddedEvents
)

data class EmbeddedEvents(
    @SerializedName("events") val events: List<Event>
)

data class Event(
    @SerializedName("name") val name: String = "",
    @SerializedName("id") val id: String = "",
    @SerializedName("dates") val dates: EventDates = EventDates(),
    @SerializedName("distance") val distance: Double? = null,
    @SerializedName("priceRanges") val priceRanges: List<PriceRange>? = emptyList(),
    @SerializedName("url") val url: String = "",
    @SerializedName("info") val info: String? = null,
    @SerializedName("pleaseNote") val pleaseNote: String? = null,
    @SerializedName("_embedded") val embedded: Embedded? = null
)

data class Embedded(
    @SerializedName("venues") val venues: List<Venue>? = emptyList()
)

data class Venue(
    @SerializedName("address") val address: Address? = null,
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = ""
)

data class Address(
    @SerializedName("line1") val line1: String = "",
    @SerializedName("line2") val line2: String = ""
)



data class EventDates(
    @SerializedName("start") val start: StartDate = StartDate()
)

data class StartDate(
    @SerializedName("localDate") val localDate: String = "",
    @SerializedName("localTime") val localTime: String = ""
)

data class PriceRange(
    @SerializedName("min") val min: Double? = null,
    @SerializedName("max") val max: Double? = null,
    @SerializedName("currency") val currency: String? = ""
)

//Maybe will use later for further details
//data class EventImage(
//    @SerializedName("url") val url: String,
//    @SerializedName("width") val width: Int,
//    @SerializedName("height") val height: Int
//)


