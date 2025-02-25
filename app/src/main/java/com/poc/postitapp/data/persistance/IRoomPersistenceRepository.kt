package com.poc.postitapp.data.persistance

import com.poc.persistence.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

interface IRoomPersistenceRepository {
    fun getAll(): Flow<List<PostItEntity>>

    fun getById(id: Int): Flow<PostItEntity>

    suspend fun insertOrUpdate(postIt: PostItEntity)

    suspend fun delete(postIt: PostItEntity)
}