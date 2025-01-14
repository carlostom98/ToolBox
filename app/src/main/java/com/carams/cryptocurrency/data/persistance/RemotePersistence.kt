package com.carams.cryptocurrency.data.persistance

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object RemotePersistence {
    private const val COUNTRIES_CHARGED = "COUNTRIES_CHARGED"
    fun getCustomPreference(activity: Activity) =
        activity.getSharedPreferences(COUNTRIES_CHARGED, Context.MODE_PRIVATE)

    private fun SharedPreferences.editPreference(edit: SharedPreferences.Editor? .() -> Unit) {
        val editObject = this.edit()
        editObject.apply(edit).apply()
    }

    var SharedPreferences.countriesValue
        get() = this.getBoolean(COUNTRIES_CHARGED, false)
        set(value) {
            this.editPreference {
                this?.putBoolean(COUNTRIES_CHARGED, value)
            }
        }

}


