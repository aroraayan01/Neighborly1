package com.example.projhope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AnnouncementsFragment : Fragment() {

    private lateinit var announcementsRecyclerView: RecyclerView
    private lateinit var announcementsContainer: LinearLayout
    private val announcementsList = mutableListOf<String>() // Sample data list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcements, container, false)

        // Initialize RecyclerView
        announcementsRecyclerView = view.findViewById(R.id.announcements_recycler_view)
        announcementsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        announcementsContainer = LinearLayout(requireContext())

        // Populate sample data
        announcementsList.add("Announcement 1: New Feature Released!")
        announcementsList.add("Announcement 2: Scheduled Maintenance Notice")
        announcementsList.add("Announcement 3: Community Event Coming Soon!")

        // Create announcement views
        createAnnouncementViews()

        // Floating Action Button click listener
        val fabNewAnnouncement: FloatingActionButton = view.findViewById(R.id.fab_new_announcement)
        fabNewAnnouncement.setOnClickListener {
            // Logic to add a new announcement
            addNewAnnouncement("New Announcement: ${System.currentTimeMillis()}")
        }

        return view
    }

    private fun createAnnouncementViews() {
        announcementsList.forEach { announcementText ->
            val announcementView = LayoutInflater.from(requireContext()).inflate(R.layout.announcement_item, announcementsContainer, false)

            // Assuming announcement_item.xml has a TextView with id announcement_text
            val textView: TextView = announcementView.findViewById(R.id.announcement_text)
            textView.text = announcementText

            announcementsContainer.addView(announcementView)
        }
        announcementsRecyclerView.adapter = AnnouncementsAdapter(announcementsList)
    }

    private fun addNewAnnouncement(text: String) {
        announcementsList.add(text)
        createAnnouncementViews()
    }

    // Inner class for AnnouncementsAdapter
    inner class AnnouncementsAdapter(private val announcements: List<String>) :
        RecyclerView.Adapter<AnnouncementsAdapter.AnnouncementViewHolder>() {

        inner class AnnouncementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val announcementTextView: TextView = view.findViewById(R.id.announcement_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.announcement_item, parent, false)
            return AnnouncementViewHolder(view)
        }

        override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
            holder.announcementTextView.text = announcements[position]
        }

        override fun getItemCount(): Int {
            return announcements.size
        }
    }
}
