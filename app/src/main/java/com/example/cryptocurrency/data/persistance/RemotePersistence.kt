package com.example.cryptocurrency.data.persistance

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.cryptocurrency.App

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

fun main(array: Array<String>) {
    val newObject = StateOfResponse().getSomeDataFromRemote {
        numberTwo = 1336
        numberOne = 1998
    }
    newObject.let {
        println("${it.numberTwo}, ${it.numberOne}")
    }

}

class StateOfResponse(var numberOne: Int ?= null, var numberTwo: Int?= null) {

    private constructor(builder: Builder) : this(builder.numberOne, builder.numberTwo)
    fun getSomeDataFromRemote(builder: Builder .() -> Unit): StateOfResponse {
        return Builder().apply(builder).printValues()
    }

    class Builder {
        var numberOne = 0
        var numberTwo = 0
        fun printValues(): StateOfResponse {
            return StateOfResponse(this)
        }
    }
}

