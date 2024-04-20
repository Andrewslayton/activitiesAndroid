package com.example.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.activitys.viewmodel.FurtherDetailsViewModel
import com.google.firebase.auth.FirebaseAuth

class FurtherDetailsActivity : AppCompatActivity() {
    private val viewModel: FurtherDetailsViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventDetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_further_detail)

        recyclerView = findViewById(R.id.eventsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EventDetailsAdapter(emptyList()) { event -> // Lambda moved outside
            val userId = FirebaseAuth.getInstance().currentUser?.uid
            userId?.let { viewModel.deselectEvent(it, event) }
        }
        recyclerView.adapter = adapter

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {
            viewModel.loadEvents(it)
        }

        viewModel.events.observe(this) { events ->
            adapter.updateEvents(events)
        }
    }
}
