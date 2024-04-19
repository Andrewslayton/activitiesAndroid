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
    @SerializedName("pleaseNote") val pleaseNote: String? = null
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



//Maybe will use later for further details
//data class EventImage(
//    @SerializedName("url") val url: String,
//    @SerializedName("width") val width: Int,
//    @SerializedName("height") val height: Int
//)
