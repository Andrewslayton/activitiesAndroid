package com.example.activitys.model

data class UserPreferences(
    val latitude: Double,
    val longitude: Double,
    val searchRadius: Int,
    val hobbies: List<String>
)
