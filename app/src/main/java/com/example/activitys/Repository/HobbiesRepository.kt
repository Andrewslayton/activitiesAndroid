import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class HobbiesRepository {

    private val db = FirebaseFirestore.getInstance()

    fun saveHobbies(userId: String, hobbies: List<String>) {
        val userHobbies = hashMapOf("hobbies" to hobbies)
        db.collection("users").document(userId).set(userHobbies,  SetOptions.merge())
            .addOnSuccessListener { /* Handle success */ }
            .addOnFailureListener { e -> /* Handle error */ }
    }

}
