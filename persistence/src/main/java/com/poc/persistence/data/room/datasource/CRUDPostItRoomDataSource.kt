package com.poc.persistence.data.room.datasource

import com.poc.persistence.data.room.daos.PostItDao
import com.poc.persistence.data.entities.PostItVO
import com.poc.persistence.domain.interfaces.PostItDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CRUDPostItRoomDataSource @Inject constructor(private val dao: PostItDao):
    PostItDataSource {

    override fun getAllSortedByTitle(): Flow<List<PostItVO>> = dao.getAllSortedByTitle()

    override fun getAll(): Flow<List<PostItVO>> = dao.getAll()

    override fun getById(id: Int): Flow<PostItVO> = dao.getById(id)

    override suspend fun insertOrUpdate(postIt: PostItVO) = dao.insert(postIt)

    override suspend fun delete(postIt: PostItVO) = dao.delete(postIt)

}