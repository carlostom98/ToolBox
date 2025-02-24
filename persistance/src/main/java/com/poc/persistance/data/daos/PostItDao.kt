package com.poc.persistance.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poc.persistance.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostItDao {

    @Query("SELECT * FROM postit")
    fun getAll(): Flow<List<PostItEntity>>

    @Query("SELECT * FROM postit WHERE id = :id")
    fun getById(id: Int): Flow<PostItEntity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(postIt: PostItEntity)

    @Delete
    suspend fun delete(postIt: PostItEntity)

}