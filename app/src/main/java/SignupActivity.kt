package com.example.projhope

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Get references to the views
        val nameEditText: EditText = findViewById(R.id.name_edittext)
        val phoneEditText: EditText = findViewById(R.id.phone_edittext)
        val emailEditText: EditText = findViewById(R.id.email_edittext)
        val passwordEditText: EditText = findViewById(R.id.password_edittext)
        val societyEditText: EditText = findViewById(R.id.society_edittext)
        val signupButton: Button = findViewById(R.id.signup_button)

        // Set up the button click listener
        signupButton.setOnClickListener {
            // Retrieve user input
            val name = nameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val society = societyEditText.text.toString().trim()

            // Validate input
            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || society.isEmpty()) {
                // Show an error message if any field is empty
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // TODO: Implement your signup logic here
            // For example, you might send the data to a server or save it locally

            // Navigate to MainActivity after successful signup
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optionally finish this activity so the user can't go back to it
        }
    }
}
