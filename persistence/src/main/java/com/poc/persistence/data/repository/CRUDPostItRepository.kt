package com.poc.persistence.data.repository

import com.poc.persistence.domain.interfaces.PostItDataSource
import com.poc.postitapp.data.interfaces.ICRUDPostItRepository
import com.poc.postitapp.domain.entities.PostItEntity
import kotlinx.coroutines.flow.Flow

class CRUDPostItRepository(private val crudRoomRepository: PostItDataSource): ICRUDPostItRepository {
    override fun getAll(): Flow<List<PostItEntity>> {
        return crudRoomRepository.getAll()
    }

    override fun getAllSortedByTitle(): Flow<List<PostItEntity>> {
        return crudRoomRepository.getAllSortedByTitle()
    }

    override fun getById(id: Int): Flow<PostItEntity> {
        return crudRoomRepository.getById(id)
    }

    override suspend fun insertOrUpdate(postIt: PostItEntity) {
        crudRoomRepository.insertOrUpdate(postIt)
    }

    override suspend fun delete(postIt: PostItEntity) {
        crudRoomRepository.delete(postIt)
    }

}
