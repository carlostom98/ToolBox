package com.poc.postitapp.data.di

import com.poc.persistence.domain.repository.CRUDPostItRepository
import com.poc.persistence.domain.repository.ICRUDPostItRepository
import com.poc.postitapp.data.persistance.IRoomPersistenceRepository
import com.poc.postitapp.data.persistance.RoomPersistenceRepository
import com.poc.postitapp.data.retrofit.CountriesAPI
import com.poc.postitapp.data.utils.DataUtils.URL_COUNTRIES
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class PersistenceRepositoryModule {
    @Binds
    abstract fun bindICRUDPostItRepository(crudPostItRepository: CRUDPostItRepository): ICRUDPostItRepository
}
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideRetrofitCountriesService(): CountriesAPI {
        return Retrofit.Builder()
            .baseUrl(URL_COUNTRIES)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountriesAPI::class.java)
    }

    @Provides
    fun provideIRoomPersistenceRepository(crudPostItRepository: ICRUDPostItRepository): IRoomPersistenceRepository {
        return RoomPersistenceRepository(crudPostItRepository)
    }

}

