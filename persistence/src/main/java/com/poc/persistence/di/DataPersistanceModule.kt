package com.poc.persistence.di

import android.content.Context
import androidx.room.Room
import com.poc.persistence.data.daos.PostItDao
import com.poc.persistence.data.database.AppDatabase
import com.poc.persistence.domain.repository.CRUDPostItRepository
import com.poc.persistence.domain.repository.ICRUDPostItRepository
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
    fun provideRepository(postItDao: PostItDao): ICRUDPostItRepository = CRUDPostItRepository(postItDao)


}