package com.example.cryptocurrency.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountriesService {
    private const val URL_COUNTRIES = "https://raw.githubusercontent.com/"
    suspend fun getService(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(URL_COUNTRIES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }
}