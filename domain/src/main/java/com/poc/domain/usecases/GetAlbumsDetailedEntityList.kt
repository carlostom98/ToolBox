package com.poc.domain.usecases

import com.poc.data.interfaces.IPersistenceRepository
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.domain.entities.AlbumEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.entities.Response
import com.poc.domain.interfaces.BaseUseCaseNoParams
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetAlbumsDetailedEntityList @Inject constructor(
    private val repositoryPhotos: IPersistenceRepository<PhotosEntity>,
    private val repositoryAlbums: IPersistenceRepository<AlbumEntity>,
) : BaseUseCaseNoParams<List<AlbumDetailedEntity>>() {

    override suspend fun doWork(): Response<List<AlbumDetailedEntity>> {
        return try {
            val albums = repositoryAlbums.getAll().firstOrNull() ?: Result.failure(Throwable())
            val photos = repositoryPhotos.getAll().firstOrNull() ?: Result.failure(Throwable())

                return when {
                    albums.isFailure -> Response.Error(albums.exceptionOrNull()?.message!!)
                    photos.isFailure -> Response.Error(albums.exceptionOrNull()?.message!!)
                    else -> {
                        val albumsData = albums.getOrNull()?.map { albums ->
                            AlbumDetailedEntity(
                                title = albums.title,
                                id = albums.id,
                                photos = photos.getOrNull()?.let {  photos ->
                                    photos.filter { it.albumId == albums.id }
                                } ?: emptyList()
                            )
                        }

                        Response.Success(albumsData)
                    }
                }

        } catch (e: Exception) {
            Response.Error(e.message!!)
        }
    }
}