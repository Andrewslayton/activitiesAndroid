package com.example.activitys.viewmodel

import HobbiesRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HobbiesViewModel(private val repository: HobbiesRepository) : ViewModel() {

    fun saveUserHobbies(userId: String, hobbies: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveHobbies(userId, hobbies)
        }
    }
}
