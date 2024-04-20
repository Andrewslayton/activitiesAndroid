package com.example.activitys

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.activitys.EventsResponse

interface TicketmasterApiService {
    @GET("discovery/v2/events.json")
    suspend fun getEvents(
        @Query("apikey") apiKey: String,
        @Query("latlong") latlong: String,
        @Query("radius") radius: String,
        @Query("unit") unit: String = "miles",
        @Query("classificationName") keyword: String? = null
    ): Response<EventsResponse>
}