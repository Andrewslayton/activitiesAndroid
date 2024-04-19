package com.example.activitys
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.activitys.Repository.MainActivityViewModelFactory
import com.example.activitys.model.UserPreferences
import com.example.activitys.viewmodel.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(RetrofitService.ticketmasterApi)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)
        viewModel.events.observe(this) { events ->
            recyclerView.adapter = EventsAdapter(events) { event ->
                saveEventToFirestore(event)
            }
        }
        fetchUserPreferencesAndLoadEvents()
    }
    private fun saveEventToFirestore(event: Event) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId).collection("selectedEvents").document(event.id).set(event)
            .addOnSuccessListener {
                Toast.makeText(this, "Event saved", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "failure to save event try again or something.", Toast.LENGTH_SHORT).show()
            }
    }
    private fun fetchUserPreferencesAndLoadEvents() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId).get().addOnSuccessListener { document ->
            if (document.exists()) {
                val latitude = document.getDouble("latitude") ?: 0.0
                val longitude = document.getDouble("longitude") ?: 0.0
                val distance = document.getLong("distance")?.toInt() ?: 0
                val hobbies = document.get("hobbies") as List<String>? ?: listOf()

                val userPreferences = UserPreferences(latitude, longitude, distance, hobbies)
                val apiKey = "VNHzOfnzhWz7553H1dhFDeHZaJNU4NZW"
                viewModel.loadEvents(userPreferences, apiKey)
            } else {
            }
        }.addOnFailureListener {
        }
    }
}