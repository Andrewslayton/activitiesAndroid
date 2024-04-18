import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore

class HobbiesRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveHobbies(userId: String, hobbies: List<String>) {
        val userHobbies = hashMapOf("hobbies" to hobbies)
        db.collection("users").document(userId).set(userHobbies)
            .addOnSuccessListener { /* Handle success */ }
            .addOnFailureListener { e -> /* Handle error */ }
    }
    fun saveUserLocationAndDistance(userId: String, latLng: LatLng, distance: Int) {
        val locationData = hashMapOf(
            "latitude" to latLng.latitude,
            "longitude" to latLng.longitude,
            "distance" to distance
        )
        db.collection("users").document(userId).update(locationData as Map<String, Any>)
            .addOnSuccessListener { /* Handle success */ }
            .addOnFailureListener { e -> /* Handle error */ }
    }

}
