package com.example.activitys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class EventDetailsAdapter(private var events: List<Event>) : RecyclerView.Adapter<EventDetailsAdapter.EventDetailViewHolder>() {

    class EventDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            itemView.findViewById<TextView>(R.id.eventNameTextView).text = event.name
            itemView.findViewById<TextView>(R.id.eventDateTimeTextView).text = "${event.dates.start.localDate} at ${event.dates.start.localTime}"
            itemView.findViewById<TextView>(R.id.eventDistanceTextView).text = "${event.distance?.toString() ?: "N/A"} miles"
            itemView.findViewById<TextView>(R.id.eventPriceRangeTextView).text = event.priceRanges?.let {
                if (it.isNotEmpty()) "Price: ${it[0].min} to ${it[0].max} ${it[0].currency}" else "No price info"
            } ?: "No price info"
            itemView.findViewById<TextView>(R.id.addressTextView).text = event.address
            itemView.findViewById<Button>(R.id.deselectButton).setOnClickListener {
                deselectEvent(event)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventDetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_details, parent, false)
        return EventDetailViewHolder(view)
    }
    fun updateEvents(newEvents: List<Event>) {
        events = newEvents
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: EventDetailViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size


}
