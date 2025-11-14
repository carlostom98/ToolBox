package com.poc.persistence.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.poc.persistence.data.converters.UrgencyLevelConverter
import com.poc.persistence.data.room.daos.PostItDao
import com.poc.persistence.data.entities.PostItVO


@Database(entities = [PostItVO::class], version = 1)
@TypeConverters(UrgencyLevelConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPostItDao(): PostItDao

    companion object {
        const val DATABASE_NAME: String = "post-it-db"
    }
}