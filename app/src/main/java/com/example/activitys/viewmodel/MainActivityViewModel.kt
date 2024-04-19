package com.example.activitys.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitys.Event
import com.example.activitys.model.UserPreferences
import com.example.activitys.TicketmasterApiService
import kotlinx.coroutines.launch

class MainActivityViewModel(private val apiService: TicketmasterApiService) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    fun loadEvents(userPreferences: UserPreferences, apiKey: String) {
        viewModelScope.launch {
            val response = apiService.getEvents(apiKey, "${userPreferences.latitude},${userPreferences.longitude}",
                userPreferences.searchRadius.toString(), "miles", userPreferences.hobbies.joinToString(","))

            if (response.isSuccessful && response.body() != null) {
                _events.postValue(response.body()?.embedded?.events ?: emptyList())
            } else {
            }
        }
    }
}
