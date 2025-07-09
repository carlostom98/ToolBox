package com.poc.postitapp.data.persistance

import com.poc.postitapp.data.interfaces.ICRUDPostItRepository
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import kotlinx.coroutines.flow.Flow

class RoomPersistenceRepository(private val crudPostItRepository: ICRUDPostItRepository):
    IRoomPersistenceRepository {

    override fun getAll(): Flow<List<PostItEntity>> = crudPostItRepository.getAll()

    override fun getAllSortedByTitle(): Flow<List<PostItEntity>> = crudPostItRepository.getAllSortedByTitle()

    override fun getById(id: Int): Flow<PostItEntity> = crudPostItRepository.getById(id)

    override suspend fun insertOrUpdate(postIt: PostItEntity) {
        crudPostItRepository.insertOrUpdate(postIt)
    }

    override suspend fun delete(postIt: PostItEntity) {
        crudPostItRepository.delete(postIt)
    }

}

