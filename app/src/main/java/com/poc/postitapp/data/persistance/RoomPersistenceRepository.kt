package com.poc.postitapp.data.persistance

import com.poc.persistence.domain.interfaces.ICRUDPostItRepository
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomPersistenceRepository(private val crudPostItRepository: ICRUDPostItRepository):
    IRoomPersistenceRepository {

    override fun getAll(): Flow<List<PostItEntity>> {
        return crudPostItRepository.getAll().map { postItVoList ->
            postItVoList.map { PostItEntity().mapFromVOToEntity(it) }
        }
    }

    override fun getAllSortedByTitle(): Flow<List<PostItEntity>> {
        return crudPostItRepository.getAllSortedByTitle().map { postItVoList ->
            postItVoList.map { PostItEntity().mapFromVOToEntity(it) }
        }
    }

    override fun getById(id: Int): Flow<PostItEntity> {
        return crudPostItRepository.getById(id).map { PostItEntity().mapFromVOToEntity(it) }
    }

    override suspend fun insertOrUpdate(postIt: PostItEntity) {
        crudPostItRepository.insertOrUpdate(postIt.mapFromEntityToVo())
    }

    override suspend fun delete(postIt: PostItEntity) {
        crudPostItRepository.delete(postIt.mapFromEntityToVo())
    }


}