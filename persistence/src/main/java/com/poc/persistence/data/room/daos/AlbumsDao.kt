package com.poc.persistence.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.persistence.data.entities.AlbumVO
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM photos ORDER BY id DESC")
    fun getAll(): Flow<List<AlbumVO>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<AlbumVO>)

}