package com.example.projhope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT

// Data model for Event
data class Event(
    val title: String,
    val date: String,
    val description: String
)

// Adapter for the RecyclerView
class EventAdapter(private val eventList: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.event_title)
        val date: TextView = itemView.findViewById(R.id.event_date)
        val description: TextView = itemView.findViewById(R.id.event_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.title.text = event.title
        holder.date.text = event.date
        holder.description.text = event.description
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}

// Fragment to display the list of events
class EventsFragment : Fragment() {

    private lateinit var eventAdapter: EventAdapter
    private lateinit var eventsRecyclerView: RecyclerView
    private lateinit var eventList: List<Event>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_events, container, false)

        // Sample event data
        eventList = listOf(
            Event("Music Concert", "Sept 30, 2024", "Join us for an evening of live music."),
            Event("Art Exhibition", "Oct 12, 2024", "Explore the latest art from local artists."),
            Event("Charity Run", "Nov 5, 2024", "Participate in a 5K run for charity.")
        )

        // Setting up RecyclerView
        eventsRecyclerView = rootView.findViewById(R.id.events_recycler_view)
        eventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(eventList)
        eventsRecyclerView.adapter = eventAdapter

        return rootView
    }
}
