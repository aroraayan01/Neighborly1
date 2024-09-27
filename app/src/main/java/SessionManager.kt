package com.example.projhope

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    companion object {
        private const val IS_LOGGED_IN = "isLoggedIn"
        private const val USER_ID = "userId"
        private const val USER_EMAIL = "userEmail"
    }

    fun setUserLoggedIn(userId: String, userEmail: String) {
        val editor = prefs.edit()
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.putString(USER_ID, userId)
        editor.putString(USER_EMAIL, userEmail)
        editor.apply()
    }

    fun isUserLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGGED_IN, false)
    }

    fun getUserId(): String? {
        return prefs.getString(USER_ID, null)
    }

    fun getUserEmail(): String? {
        return prefs.getString(USER_EMAIL, null)
    }

    fun logout() {
        val editor = prefs.edit()
        editor.clear()
        editor.apply()
    }

}
