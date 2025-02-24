package com.poc.persistance.domain.repository

import com.poc.persistance.data.daos.PostItDao
import com.poc.persistance.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CRUDPostitRepository @Inject constructor(private val dao: PostItDao): ICRUDPostitRepository {
    override fun getAll(): Flow<List<PostItEntity>> = dao.getAll()

    override fun getById(id: Int): Flow<PostItEntity> = dao.getById(id)

    override suspend fun insertOrUpdate(postIt: PostItEntity) = dao.insert(postIt)

    override suspend fun delete(postIt: PostItEntity) = dao.delete(postIt)

}