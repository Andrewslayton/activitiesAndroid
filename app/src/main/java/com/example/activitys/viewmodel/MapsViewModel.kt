//package com.example.activitys.viewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.activitys.model.LatLng
//import com.example.activitys.repository.LocationRepository
//import kotlinx.coroutines.launch
//
//
//class MapsViewModel(private val repository: LocationRepository) : ViewModel() {
//
//    fun saveUserLocationAndDistance(userId: String, latLng: LatLng, distance: Int) {
//        viewModelScope.launch {
//            repository.saveUserLocationAndDistance(userId, latLng, distance)
//        }
//    }
//}
