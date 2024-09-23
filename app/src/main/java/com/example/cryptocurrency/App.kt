package com.example.cryptocurrency

import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        private  var instance: App? = null
        fun getApplicationContext(): Context? = instance
    }
    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}