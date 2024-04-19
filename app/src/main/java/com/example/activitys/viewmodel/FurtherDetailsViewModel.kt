package com.example.activitys.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.activitys.Event
import com.google.firebase.firestore.FirebaseFirestore

class FurtherDetailsViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> get() = _events

    fun loadEvents(userId: String) {
        db.collection("users").document(userId).collection("selectedEvents")
            .get()
            .addOnSuccessListener { result ->
                val eventsList = result.documents.mapNotNull { doc ->
                    doc.toObject(Event::class.java)
                }
                _events.value = eventsList
            }
            .addOnFailureListener { exception ->
                // Handle any errors here, perhaps logging them or displaying a message
            }
    }
}
