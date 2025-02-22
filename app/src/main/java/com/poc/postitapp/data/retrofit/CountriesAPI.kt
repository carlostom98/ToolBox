package com.poc.postitapp.data.retrofit

import com.poc.postitapp.domain.entities.CountriesEntity
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<CountriesEntity>>
}
