package com.poc.persistence.domain.repository


import com.poc.persistence.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

interface ICRUDPostItRepository {

    fun getAll(): Flow<List<PostItEntity>>

    fun getById(id: Int): Flow<PostItEntity>

    suspend fun insertOrUpdate(postIt: PostItEntity)

    suspend fun delete(postIt: PostItEntity)
}