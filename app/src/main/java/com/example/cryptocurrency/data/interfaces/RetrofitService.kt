package com.example.cryptocurrency.data.interfaces

import com.example.cryptocurrency.data.retrofit.CountriesAPI

interface RetrofitService {
    suspend fun getService(): Result<CountriesAPI>
}