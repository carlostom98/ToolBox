package com.poc.persistance.di

import android.content.Context
import androidx.room.Room
import com.poc.persistance.data.daos.PostItDao
import com.poc.persistance.data.database.AppDatabase
import com.poc.persistance.domain.repository.CRUDPostitRepository
import com.poc.persistance.domain.repository.ICRUDPostitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataPersistanceModule {

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
    fun provideDao(appDatabase: AppDatabase) = appDatabase.getPostItDao()

    @Provides
    @Singleton
    fun provideRepository(postItDao: PostItDao): ICRUDPostitRepository = CRUDPostitRepository(postItDao)


}