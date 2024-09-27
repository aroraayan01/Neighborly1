package com.example.projhope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class HomeFragment : Fragment() {

    private lateinit var adapter: TimelineAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventList: List<String> // Placeholder for events

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.timeline_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Vertical layout

        // Sample data (you can populate this based on selected date later)
        eventList = listOf("Event 1", "Event 2", "Event 3") // Replace with dynamic data

        // Initialize adapter with sample data
        adapter = TimelineAdapter(eventList)
        recyclerView.adapter = adapter

        // Handle CalendarView date selection
        val calendarView = view.findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(requireContext(), "Selected: $selectedDate", Toast.LENGTH_SHORT).show()

            // Here you can retrieve or generate dynamic events based on the selected date
            val dynamicEvents = listOf("Meeting on $selectedDate", "Deadline on $selectedDate", "Party on $selectedDate") // Replace with your event logic
            adapter.updateEvents(dynamicEvents)
        }

        return view
    }

    // Inner class for TimelineAdapter
    inner class TimelineAdapter(private var eventList: List<String>) :
        RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>() {

        inner class TimelineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val eventTextView: TextView = view.findViewById(R.id.event_text)
            val timelineLine: View = view.findViewById(R.id.timeline_line)
            val timelineCircle: View = view.findViewById(R.id.timeline_circle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
            // Inflate the custom layout for each event
            val view = LayoutInflater.from(parent.context).inflate(R.layout.timeline_item, parent, false)
            return TimelineViewHolder(view)
        }

        override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {
            holder.eventTextView.text = eventList[position]
        }

        override fun getItemCount(): Int {
            // Return the number of events in the list
            return eventList.size
        }

        // Method to update the event list
        fun updateEvents(newEventList: List<String>) {
            eventList = newEventList
            notifyDataSetChanged() // Notify RecyclerView to refresh data
        }
    }
}
