package com.poc.persistence.data.repository

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.persistence.domain.interfaces.ICRUDPostItRepository
import com.poc.persistence.domain.interfaces.PostItDataSource
import kotlinx.coroutines.flow.Flow

class CRUDPostItRepository(private val crudRoomRepository: PostItDataSource): ICRUDPostItRepository {
    override fun getAll(): Flow<List<PostItVO>> {
        return crudRoomRepository.getAll()
    }

    override fun getAllSortedByTitle(): Flow<List<PostItVO>> {
        return crudRoomRepository.getAllSortedByTitle()
    }

    override fun getById(id: Int): Flow<PostItVO> {
        return crudRoomRepository.getById(id)
    }

    override suspend fun insertOrUpdate(postIt: PostItVO) {
        crudRoomRepository.insertOrUpdate(postIt)
    }

    override suspend fun delete(postIt: PostItVO) {
        crudRoomRepository.delete(postIt)
    }

}
