package com.example.activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(
    private var events: MutableList<Event>,
    private val onSelect: (Event, Int) -> Unit
) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, onSelect: (Event, Int) -> Unit, position: Int) {
            val nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
            val dateTextView = itemView.findViewById<TextView>(R.id.dateTextView)
            val selectButton = itemView.findViewById<Button>(R.id.selectButton)

            nameTextView.text = event.name
            dateTextView.text = "${event.dates.start.localDate} at ${event.dates.start.localTime}"

            selectButton.setOnClickListener { onSelect(event, position) }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position], onSelect, position)
    }
    override fun getItemCount(): Int = events.size
    fun removeAt(position: Int) {
        if (position >= 0 && position < events.size) {
            events.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, events.size)
        }
    }
}

