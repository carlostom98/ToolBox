package com.poc.postitapp.data.persistance

import com.poc.persistence.domain.entities.PostItEntity
import com.poc.persistence.domain.repository.CRUDPostItRepository
import com.poc.persistence.domain.repository.ICRUDPostItRepository
import kotlinx.coroutines.flow.Flow

class RoomPersistenceRepository(private val crudPostItRepository: ICRUDPostItRepository): IRoomPersistenceRepository {
    override fun getAll(): Flow<List<PostItEntity>> = crudPostItRepository.getAll()

    override fun getById(id: Int): Flow<PostItEntity> = crudPostItRepository.getById(id)

    override suspend fun insertOrUpdate(postIt: PostItEntity) {
        crudPostItRepository.insertOrUpdate(postIt)
    }

    override suspend fun delete(postIt: PostItEntity) {
        crudPostItRepository.delete(postIt)
    }

    override fun getAllSortedByTitle(): Flow<List<PostItEntity>> = crudPostItRepository.getAllSortedByTitle()

}