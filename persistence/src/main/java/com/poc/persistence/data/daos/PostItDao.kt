package com.poc.persistence.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.persistence.data.entitiesdb.PostItVO
import kotlinx.coroutines.flow.Flow

@Dao
interface PostItDao {

    @Query("SELECT * FROM postit ORDER BY title DESC")
    fun getAll(): Flow<List<PostItVO>>

    @Query("SELECT * FROM postit ORDER BY title ASC")
    fun getAllSortedByTitle(): Flow<List<PostItVO>>

    @Query("SELECT * FROM postit WHERE id = :id")
    fun getById(id: Int): Flow<PostItVO>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postIt: PostItVO)

    @Delete
    suspend fun delete(postIt: PostItVO)

}