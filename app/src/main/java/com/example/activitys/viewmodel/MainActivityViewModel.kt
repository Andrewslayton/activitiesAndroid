package com.example.activitys.viewmodel

import com.example.activitys.Repository.EventsRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitys.Event
import com.example.activitys.model.UserPreferences
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: EventsRepository) : ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    fun loadEvents(userPreferences: UserPreferences) {
        viewModelScope.launch {
            try {
                val events = repository.fetchEvents(
                    userPreferences.latitude,
                    userPreferences.longitude,
                    userPreferences.searchRadius,
                    userPreferences.hobbies
                )
                _events.value = events
            } catch (e: Exception) {
                // Handle exceptions

            }
        }
    }
}
