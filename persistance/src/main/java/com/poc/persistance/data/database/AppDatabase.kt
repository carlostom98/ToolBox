package com.poc.persistance.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poc.persistance.data.converters.UrgencyLevelConverter
import com.poc.persistance.data.daos.PostItDao
import com.poc.persistance.domain.entities.PostItEntity


@Database(entities = [PostItEntity::class], version = 1)
@TypeConverters(UrgencyLevelConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPostItDao(): PostItDao

    companion object {
        const val DATABASE_NAME: String = "post-it-db"
    }
}