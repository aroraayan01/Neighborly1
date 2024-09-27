package com.example.projhope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GroupsFragment : Fragment() {

    private lateinit var groupsRecyclerView: RecyclerView
    private lateinit var adapter: GroupsAdapter // Adapter for managing group items
    private lateinit var groupsList: MutableList<String> // List to hold groups

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_groups, container, false)

        // Initialize RecyclerView
        groupsRecyclerView = view.findViewById(R.id.groups_recycler_view)
        groupsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Sample data (replace with actual group data)
        groupsList = mutableListOf("Group 1", "Group 2", "Group 3") // Replace with dynamic data

        // Initialize adapter
        adapter = GroupsAdapter(groupsList)
        groupsRecyclerView.adapter = adapter

        // Handle FAB click to create a new group
        val fabNewGroup = view.findViewById<FloatingActionButton>(R.id.fab_new_group)
        fabNewGroup.setOnClickListener {
            showCreateGroupDialog()
        }

        return view
    }

    private fun showCreateGroupDialog() {
        // Create an EditText for input
        val editText = EditText(requireContext())
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Create New Group")
            .setMessage("Enter group name:")
            .setView(editText)
            .setPositiveButton("Create") { _, _ ->
                val newGroupName = editText.text.toString().trim()
                if (newGroupName.isNotEmpty()) {
                    adapter.addGroup(newGroupName)
                    Toast.makeText(requireContext(), "Group created: $newGroupName", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Group name cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    // Inner class for GroupsAdapter
    inner class GroupsAdapter(private var groupsList: MutableList<String>) :
        RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

        inner class GroupsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val groupTextView: TextView = view.findViewById(R.id.group_name_text)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.group_item, parent, false)
            return GroupsViewHolder(view)
        }

        override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
            holder.groupTextView.text = groupsList[position]
        }

        override fun getItemCount(): Int {
            return groupsList.size
        }

        // Method to add a new group to the list and notify the adapter
        fun addGroup(newGroup: String) {
            groupsList.add(newGroup)
            notifyItemInserted(groupsList.size - 1) // Notify that an item was added
        }
    }
}
