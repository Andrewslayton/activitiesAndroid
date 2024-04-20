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
            }
    }
    fun deselectEvent(userId: String, event: Event) {
        db.collection("users").document(userId).collection("selectedEvents").document(event.id)
            .delete()
            .addOnSuccessListener {
            }
    }
}
