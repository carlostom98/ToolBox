package com.example.cryptocurrency.data.retrofit

import com.example.cryptocurrency.domain.entities.CountriesEntity
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI: API {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<CountriesEntity>>
}

interface API