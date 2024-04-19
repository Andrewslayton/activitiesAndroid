//package com.example.activitys.Repository
//
//
//import com.google.firebase.firestore.FirebaseFirestore
//import com.example.activitys.model.LatLng
//
//class LocationRepository {
//
//    private val db = FirebaseFirestore.getInstance()
//
//    fun saveUserLocationAndDistance(userId: String, latLng: LatLng, distance: Int) {
//        val locationData = hashMapOf(
//            "latitude" to latLng.latitude,
//            "longitude" to latLng.longitude,
//            "distance" to distance
//        )
//        db.collection("users").document(userId).update(locationData as Map<String, Any>)
//            .addOnSuccessListener { /* Handle success */ }
//            .addOnFailureListener { e -> /* Handle error */ }
//    }
//}