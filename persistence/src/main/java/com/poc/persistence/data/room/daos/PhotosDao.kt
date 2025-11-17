package com.poc.persistence.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.persistence.data.entities.AlbumVO
import com.poc.persistence.data.entities.PhotosVO
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotosDao {

    @Query("SELECT * FROM photos ORDER BY albumId DESC")
    fun getAll(): Flow<List<PhotosVO>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photos: List<PhotosVO>)


}