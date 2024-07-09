package com.example.petwalk.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    companion object {
        private var instance: SharedPreferencesManager? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = SharedPreferencesManager(context)
            }
        }

        fun getInstance(): SharedPreferencesManager {
            return instance ?: throw IllegalStateException("SharedPreferencesManager is not initialized")
        }
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString("auth_token", token)
        editor.apply()
    }

    fun saveRefreshToken(token: String) {
        val editor = prefs.edit()
        editor.putString("refresh_token", token)
        editor.apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString("auth_token", null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString("refresh_token", null)
    }

    fun clearTokens() {
        val editor = prefs.edit()
        editor.remove("auth_token")
        editor.remove("refresh_token")
        editor.apply()
    }
}
