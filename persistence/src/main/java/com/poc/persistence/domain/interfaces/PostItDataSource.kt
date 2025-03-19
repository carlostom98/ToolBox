package com.poc.persistence.domain.interfaces

import com.poc.persistence.data.entitiesdb.PostItVO
import kotlinx.coroutines.flow.Flow

interface PostItDataSource {
    fun getAll(): Flow<List<PostItVO>>

    fun getAllSortedByTitle(): Flow<List<PostItVO>>

    fun getById(id: Int): Flow<PostItVO>

    suspend fun insertOrUpdate(postIt: PostItVO)

    suspend fun delete(postIt: PostItVO)
}