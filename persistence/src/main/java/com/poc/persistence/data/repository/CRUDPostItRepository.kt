package com.poc.persistence.data.repository

import com.extensions.toPostItEntity
import com.extensions.toPostItVo
import com.poc.data.interfaces.ICRUDPostItRepository
import com.poc.domain.entities.PostItEntity
import com.poc.persistence.domain.interfaces.PostItDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CRUDPostItRepository(private val crudRoomRepository: PostItDataSource): ICRUDPostItRepository {
    override fun getAll(): Flow<List<PostItEntity>> {
        return crudRoomRepository.getAll().map { postItVoList ->
            postItVoList.map { it.toPostItEntity() }
        }
    }

    override fun getAllSortedByTitle(): Flow<List<PostItEntity>> {
        return crudRoomRepository.getAllSortedByTitle().map { postItVoList ->
            postItVoList.map { it.toPostItEntity() }
        }
    }

    override fun getById(id: Int): Flow<PostItEntity> {
        return crudRoomRepository.getById(id).map { it.toPostItEntity() }
    }

    override suspend fun insertOrUpdate(postIt: PostItEntity) {
        crudRoomRepository.insertOrUpdate(postIt.toPostItVo())
    }

    override suspend fun delete(postIt: PostItEntity) {
        crudRoomRepository.delete(postIt.toPostItVo())
    }

}
