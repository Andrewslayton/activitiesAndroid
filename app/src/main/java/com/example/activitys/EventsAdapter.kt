package com.example.activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(private val events: List<Event>, private val onSelect: (Event) -> Unit) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, onSelect: (Event) -> Unit) {
            itemView.findViewById<TextView>(R.id.nameTextView).text = event.name
            itemView.findViewById<TextView>(R.id.dateTextView).text = "${event.dates.start.localDate} at ${event.dates.start.localTime}"
            itemView.findViewById<Button>(R.id.selectButton).setOnClickListener { onSelect(event) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], onSelect)
    }

    override fun getItemCount(): Int = events.size
}

