
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.MutableLiveData


class HobbiesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    val saveSuccessful = MutableLiveData<Boolean>()

    fun saveUserHobbies(userId: String, hobbies: List<String>) {
        val userHobbies = hashMapOf("hobbies" to hobbies)
        db.collection("users").document(userId).set(userHobbies)
            .addOnSuccessListener {
                saveSuccessful.value = true
            }
            .addOnFailureListener { e ->
                saveSuccessful.value = false
            }
    }
}
