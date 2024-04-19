package com.example.activitys

import com.google.gson.annotations.SerializedName

data class EventsResponse(
    @SerializedName("_embedded") val embedded: EmbeddedEvents
)

data class EmbeddedEvents(
    @SerializedName("events") val events: List<Event>
)

data class Event(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("dates") val dates: EventDates,
    @SerializedName("images") val images: List<EventImage>,
    @SerializedName("info") val info: String?,
    @SerializedName("pleaseNote") val pleaseNote: String?
)

data class EventDates(
    @SerializedName("start") val start: StartDate
)

data class StartDate(
    @SerializedName("localDate") val localDate: String,
    @SerializedName("localTime") val localTime: String
)

data class EventImage(
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int
)
