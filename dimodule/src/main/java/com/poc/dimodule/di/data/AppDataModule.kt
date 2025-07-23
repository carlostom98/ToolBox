package com.poc.dimodule.di.data


import com.poc.data.interfaces.ICRUDPostItRepository
import com.poc.data.persistance.RoomPersistenceRepository
import com.poc.domain.interfaces.IRoomPersistenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {
    @Provides
    fun provideIRoomPersistenceRepository(crudPostItRepository: ICRUDPostItRepository): IRoomPersistenceRepository {
        return RoomPersistenceRepository(crudPostItRepository)
    }
}