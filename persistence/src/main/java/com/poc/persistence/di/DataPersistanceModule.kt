package com.poc.persistence.di

import android.content.Context
import androidx.room.Room
import com.poc.data.interfaces.ICRUDPostItRepository
import com.poc.persistence.data.daos.PostItDao
import com.poc.persistence.data.database.AppDatabase
import com.poc.persistence.data.datasource.CRUDPostItRoomDataSource
import com.poc.persistence.data.repository.CRUDPostItRepository
import com.poc.persistence.domain.interfaces.PostItDataSource
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
    fun provideDao(appDatabase: AppDatabase) = appDatabase.getPostItDao()

    @Provides
    @Singleton
    fun provideRoomDataSource(postItDao: PostItDao): PostItDataSource = CRUDPostItRoomDataSource(postItDao)

    @Provides
    @Singleton
    fun provideRepository(roomDataSource: PostItDataSource): ICRUDPostItRepository = CRUDPostItRepository(roomDataSource)


}