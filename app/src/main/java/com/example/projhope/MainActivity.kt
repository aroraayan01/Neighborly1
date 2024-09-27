package com.example.projhope

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)

        // Check if user is logged in
        if (!sessionManager.isUserLoggedIn()) {
            // Redirect to login activity if not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Set up the DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Handle navigation view item clicks
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    showFragment(HomeFragment())
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_profile -> {
                    Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_settings -> {
                    Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.nav_logout -> {
                    logout() // Line 47: Calls the updated logout method
                    true
                }
                else -> false
            }
        }

        // Set up BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    showFragment(HomeFragment())
                    true
                }
                R.id.navigation_services -> {
                    showFragment(ServicesFragment())
                    true
                }
                R.id.navigation_groups -> {
                    showFragment(GroupsFragment())
                    true
                }
                R.id.navigation_events -> {
                    showFragment(EventsFragment())
                    true
                }
                R.id.navigation_announcements -> {
                    showFragment(AnnouncementsFragment())
                    true
                }
                else -> false
            }
        }

        if (savedInstanceState == null) {
            showFragment(HomeFragment())
            bottomNavigationView.selectedItemId = R.id.navigation_home
        }

        // Widen the sliding area
        val drawerWidth = resources.getDimensionPixelSize(R.dimen.drawer_width)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, GravityCompat.START)

        val touchListener = View.OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (event.rawX < drawerWidth) {
                    if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        drawerLayout.openDrawer(GravityCompat.START)
                    }
                }
            }
            v.onTouchEvent(event)
        }

        findViewById<View>(R.id.content_frame).setOnTouchListener(touchListener)

        // Set up the logout button in the drawer directly (not in the header)
        val logoutButton: Button = findViewById(R.id.nav_logout_button)
        logoutButton.setOnClickListener {
            logout() // Line 83: Calls the updated logout method
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, fragment)
            .commit()
    }

    private fun logout() { // Line 90: This is where the logout method is defined
        sessionManager.logout() // Clears session data
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK // Ensures that the back stack is cleared
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_message -> {
                Toast.makeText(this, "Message icon clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
