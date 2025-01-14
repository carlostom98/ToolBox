package com.carams.cryptocurrency.data.di

import com.carams.cryptocurrency.data.retrofit.CountriesAPI
import com.carams.cryptocurrency.data.utils.DataUtils.URL_COUNTRIES
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

