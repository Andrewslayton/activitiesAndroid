package com.example.activitys.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.activitys.TicketmasterApiService
import com.example.activitys.viewmodel.MainActivityViewModel

class MainActivityViewModelFactory(private val apiService: TicketmasterApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}