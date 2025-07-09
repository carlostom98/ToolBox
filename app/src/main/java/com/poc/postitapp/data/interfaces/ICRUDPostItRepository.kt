package com.poc.postitapp.data.interfaces

import com.poc.postitapp.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

interface ICRUDPostItRepository {
    fun getAll(): Flow<List<PostItEntity>>

    fun getAllSortedByTitle(): Flow<List<PostItEntity>>

    fun getById(id: Int): Flow<PostItEntity>

    suspend fun insertOrUpdate(postIt: PostItEntity)

    suspend fun delete(postIt: PostItEntity)
}