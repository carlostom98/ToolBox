package com.poc.postitapp.domain.interfaces

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

interface IRoomPersistenceRepository {
    fun getAll(): Flow<List<PostItEntity>>

    fun getAllSortedByTitle(): Flow<List<PostItEntity>>

    fun getById(id: Int): Flow<PostItEntity>

    suspend fun insertOrUpdate(postIt: PostItEntity)

    suspend fun delete(postIt: PostItEntity)
}