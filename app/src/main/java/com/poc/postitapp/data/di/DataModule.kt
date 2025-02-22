package com.poc.postitapp.data.di

import com.poc.postitapp.data.retrofit.CountriesAPI
import com.poc.postitapp.data.utils.DataUtils.URL_COUNTRIES
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun getRetrofitCountriesService(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(URL_COUNTRIES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }

}

