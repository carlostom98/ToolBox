package com.carams.cryptocurrency.data.retrofit

import com.carams.cryptocurrency.domain.entities.CountriesEntity
import retrofit2.Response
import retrofit2.http.GET

interface CountriesAPI {
    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<CountriesEntity>>
}
