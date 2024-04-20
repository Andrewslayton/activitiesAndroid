package com.example.activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventDetailsAdapter(
    private var events: MutableList<Event>,
    private val onDeselect: (Event, Int) -> Unit) : RecyclerView.Adapter<EventDetailsAdapter.EventDetailViewHolder>() {

    class EventDetailViewHolder(itemView: View, private val onDeselect: (Event, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event, position: Int) {
            itemView.findViewById<TextView>(R.id.eventNameTextView).text = event.name
            itemView.findViewById<TextView>(R.id.eventDateTimeTextView).text = "${event.dates.start.localDate} at ${event.dates.start.localTime}"
            itemView.findViewById<TextView>(R.id.eventDistanceTextView).text = "${event.distance?.toString() ?: "N/A"} miles"
            itemView.findViewById<TextView>(R.id.eventPriceRangeTextView).text = event.priceRanges?.let {
                if (it.isNotEmpty()) "Price: ${it[0].min} to ${it[0].max} ${it[0].currency}" else "No price info"
            } ?: "No price info"
            itemView.findViewById<TextView>(R.id.addressTextView).text = event.embedded?.venues?.firstOrNull()?.address?.line1 ?: "No address ready yet"
            itemView.findViewById<Button>(R.id.deselectButton).setOnClickListener {
                onDeselect(event, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_details, parent, false)
        return EventDetailViewHolder(view, onDeselect)
    }

    override fun onBindViewHolder(holder: EventDetailViewHolder, position: Int) {
        holder.bind(events[position], position)
    }

    override fun getItemCount() = events.size

    fun updateEvents(newEvents: List<Event>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

    fun removeEventAt(position: Int) {
        if (position >= 0 && position < events.size) {
            events.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, events.size)
        }
    }

}


