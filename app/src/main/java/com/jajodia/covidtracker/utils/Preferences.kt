package com.jajodia.covidtracker.utils

import androidx.preference.PreferenceManager
import java.lang.Exception

/**
 * Created by Aditya V Jajodia on 11-07-2020.
 */

class Preferences {

    companion object {

        fun putPref(key: String, value: String) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(ResourceProviders.getApplicationContext())
            val editor = prefs.edit()
            editor.putString(key, value)
            editor.apply()
        }

        fun putPref(key: String, value: Boolean) {
            val prefs = PreferenceManager.getDefaultSharedPreferences(ResourceProviders.getApplicationContext())
            val editor = prefs.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        fun clearPreferences() {
            val prefs = PreferenceManager.getDefaultSharedPreferences(ResourceProviders.getApplicationContext())
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
        }

        @Suppress("UNCHECKED_CAST")
        fun <T : Any> getPref(key: String): T? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ResourceProviders.getApplicationContext())

            return try {
                preferences.all.getValue(key) as T

            } catch (e: Exception) {
                e.printStackTrace()

                null
            }
        }

        fun checkIfPreferenceKeyExists(key: String): Boolean {
            val prefs = PreferenceManager.getDefaultSharedPreferences(ResourceProviders.getApplicationContext())
            return prefs.contains(key)
        }
    }

}