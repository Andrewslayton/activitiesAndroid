import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.activitys.HobbiesActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class HobbiesViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    fun saveUserHobbies(userId: String, hobbies: List<String>) {
        viewModelScope.launch {
            val userHobbies = hashMapOf("hobbies" to hobbies)
            db.collection("users").document(userId).set(userHobbies)
                .addOnSuccessListener {
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        }
    }
}
