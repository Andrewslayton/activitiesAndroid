package com.example.activitys
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.activitys.model.UserPreferences
import com.example.activitys.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.eventsRecyclerView)

        viewModel.events.observe(this) { events ->
            recyclerView.adapter = EventsAdapter(events)
        }

        // Assume getUserPreferences is a method that fetches user preferences
        viewModel.loadEvents(getUserPreferences())
    }

    private fun getUserPreferences(): UserPreferences {
        // This should actually fetch data from Firestore or your preferred storage
        return UserPreferences(34.0522, -118.2437, 100, listOf("Music", "Sports"))
    }
}
