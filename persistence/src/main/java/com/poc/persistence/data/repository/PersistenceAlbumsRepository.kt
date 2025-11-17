package com.poc.persistence.data.repository

import com.poc.data.interfaces.IPersistenceRepository
import com.poc.domain.entities.AlbumEntity
import com.poc.domain.entities.Response
import com.poc.persistence.data.entities.AlbumVO
import com.poc.persistence.data.retrofit.APIService
import com.poc.persistence.data.room.daos.AlbumsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class PersistenceAlbumsRepository( private val albumsDao: AlbumsDao,
                                   private val apiService: APIService
): IPersistenceRepository<AlbumVO, AlbumEntity> {

    override fun getAll(): Flow<Response<List<AlbumEntity>>> = flow {
        emit(Response.Loading)
        val cache = albumsDao.getAll().firstOrNull().orEmpty().map {
            it.voToAlbumEntity()
        }

        emit(Response.Success(cache))

        try {
            val remote = apiService.getAlbumsData()
            if (remote.isSuccessful) {
                remote.body()?.let {
                    albumsDao.insert(it)
                }  ?: emit(Response.Error("Api body is NULL"))
            } else {
                emit(Response.Error("Error recovering API Data"))
            }

        } catch (e: Exception) {
            emit(Response.Error(e.message!!))
        }


        emitAll(  albumsDao.getAll().map { list ->
            val mapped = list.map { it.voToAlbumEntity() }
            Response.Success(mapped)
        })
    }

}