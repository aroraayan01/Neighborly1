package com.example.projhope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.cardview.widget.CardView

class ServicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_services, container, false)

        // Accessing the service options by their IDs
        val serviceOption1 = view.findViewById<CardView>(R.id.service_option_1)
        val serviceOption2 = view.findViewById<CardView>(R.id.service_option_2)
        val serviceOption3 = view.findViewById<CardView>(R.id.service_option_3)
        val serviceOption4 = view.findViewById<CardView>(R.id.service_option_4)

        // Set up click listeners or other logic as needed
        serviceOption1.setOnClickListener {
            // Handle click for Service 1
        }

        serviceOption2.setOnClickListener {
            // Handle click for Service 2
        }

        serviceOption3.setOnClickListener {
            // Handle click for Service 3
        }

        serviceOption4.setOnClickListener {
            // Handle click for Service 4
        }

        return view
    }
}
