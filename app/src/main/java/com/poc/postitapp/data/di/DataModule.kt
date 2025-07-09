package com.poc.postitapp.data.di

import com.poc.postitapp.data.interfaces.ICRUDPostItRepository
import com.poc.postitapp.data.persistance.RoomPersistenceRepository
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideIRoomPersistenceRepository(crudPostItRepository: ICRUDPostItRepository): IRoomPersistenceRepository {
        return RoomPersistenceRepository(crudPostItRepository)
    }

}

