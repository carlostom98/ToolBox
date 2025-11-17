package com.poc.persistence.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poc.persistence.data.entities.AlbumVO
import com.poc.persistence.data.entities.PhotosVO
import com.poc.persistence.data.room.daos.AlbumsDao
import com.poc.persistence.data.room.daos.PhotosDao


@Database(entities = [AlbumVO::class, PhotosVO::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPhotosDao(): PhotosDao
    abstract fun getAlbumsDao(): AlbumsDao

    companion object {
        const val DATABASE_NAME: String = "persistence-db"
    }
}