package com.example.cryptocurrency.data.retrofit

import android.util.Log
import com.example.cryptocurrency.App
import com.example.cryptocurrency.R
import com.example.cryptocurrency.data.interfaces.RetrofitService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {
    private val urlCountries = App.getApplicationContext()?.getString(R.string.countries_link)
    suspend fun getService(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(urlCountries!!)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }
}