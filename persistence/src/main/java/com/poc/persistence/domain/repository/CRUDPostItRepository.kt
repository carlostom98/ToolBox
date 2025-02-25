package com.poc.persistence.domain.repository

import com.poc.persistence.data.daos.PostItDao
import com.poc.persistence.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CRUDPostItRepository @Inject constructor(private val dao: PostItDao): ICRUDPostItRepository {
    override fun getAll(): Flow<List<PostItEntity>> = dao.getAll()

    override fun getById(id: Int): Flow<PostItEntity> = dao.getById(id)

    override suspend fun insertOrUpdate(postIt: PostItEntity) = dao.insert(postIt)

    override suspend fun delete(postIt: PostItEntity) = dao.delete(postIt)

}