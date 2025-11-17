package com.poc.persistence.data.repository

import com.poc.data.interfaces.IPersistenceRepository
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.entities.Response
import com.poc.persistence.data.entities.PhotosVO
import com.poc.persistence.data.retrofit.APIService
import com.poc.persistence.data.room.daos.PhotosDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map
import kotlin.collections.orEmpty

class PersistencePhotosRepository(
    private val photosDao: PhotosDao,
    private val apiService: APIService
    ): IPersistenceRepository<PhotosVO, PhotosEntity> {
    override fun getAll(): Flow<Response<List<PhotosEntity>>> = flow {
        emit(Response.Loading)
        val cache = photosDao.getAll().firstOrNull().orEmpty().map {
            it.voToPhotosEntity()
        }

        emit(Response.Success(cache))

        try {
            val remote = apiService.getPhotosData()
            if (remote.isSuccessful) {
                remote.body()?.let {
                    photosDao.insert(it)
                }  ?: emit(Response.Error("Api body is NULL"))
            } else {
                emit(Response.Error("Error recovering API Data"))
            }

        } catch (e: Exception) {
            emit(Response.Error(e.message!!))
        }


        emitAll(  photosDao.getAll().map { list ->
            val mapped = list.map { it.voToPhotosEntity() }
            Response.Success(mapped)
        })
    }


}
