package com.poc.persistence.di

import android.content.Context
import androidx.room.Room
import com.poc.data.interfaces.IPersistenceRepository
import com.poc.persistence.data.room.daos.PhotosDao
import com.poc.persistence.data.room.database.AppDatabase
import com.poc.persistence.data.repository.PersistencePhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDataBase( @ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
           appContext,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePhotosDao(appDatabase: AppDatabase) = appDatabase.getPhotosDao()


    @Provides
    @Singleton
    fun provideAlbumsDao(appDatabase: AppDatabase) = appDatabase.getAlbumsDao()



}