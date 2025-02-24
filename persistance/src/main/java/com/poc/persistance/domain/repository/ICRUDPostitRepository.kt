package com.poc.persistance.domain.repository


import com.poc.persistance.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

interface ICRUDPostitRepository {

    fun getAll(): Flow<List<PostItEntity>>

    fun getById(id: Int): Flow<PostItEntity>

    suspend fun insertOrUpdate(postIt: PostItEntity)

    suspend fun delete(postIt: PostItEntity)
}