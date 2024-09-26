package com.example.cryptocurrency.data.persistance

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.cryptocurrency.App

object RemotePersistence {
    private const val COUNTRIES_CHARGED = "COUNTRIES_CHARGED"
    fun getCustomPreference(activity: Activity) =
        activity.getSharedPreferences(COUNTRIES_CHARGED, Context.MODE_PRIVATE)

    private fun SharedPreferences.editPreference(edit: (editor: SharedPreferences.Editor?) -> Unit) {
        val editObject = this.edit()
        editObject?.let {
            edit(it)
            it.apply()
        }
    }

    var SharedPreferences.countriesValue
        get() = this.getBoolean(COUNTRIES_CHARGED, false)
        set(value) {
            this.editPreference {
                it?.putBoolean(COUNTRIES_CHARGED, value!!)
            }
        }

}