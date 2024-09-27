package com.example.projhope

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        if (sessionManager.isUserLoggedIn()) {
            // Redirect to MainActivity if already logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        val loginButton: Button = findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            // Perform login operation
            // For demonstration purposes, hardcoding userId and userEmail
            sessionManager.setUserLoggedIn("user_id", "user_email@example.com")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
