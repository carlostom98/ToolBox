package com.poc.dimodule.di.data


import com.poc.data.interfaces.IPersistenceRepository
import com.poc.data.persistance.RoomPersistenceRepository
import com.poc.domain.entities.AlbumEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.interfaces.IRoomPersistenceRepository
import com.poc.persistence.data.entities.AlbumVO
import com.poc.persistence.data.entities.PhotosVO
import com.poc.persistence.data.repository.PersistenceAlbumsRepository
import com.poc.persistence.data.repository.PersistencePhotosRepository
import com.poc.persistence.data.retrofit.APIService
import com.poc.persistence.data.room.daos.AlbumsDao
import com.poc.persistence.data.room.daos.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDataModule {
    @Provides
    @Singleton
    fun providePersistenceAlbumsRepository(apiService: APIService, albumsDao: AlbumsDao): IPersistenceRepository<AlbumVO, AlbumEntity> =
        PersistenceAlbumsRepository(albumsDao = albumsDao, apiService = apiService)


    @Provides
    @Singleton
    fun providePersistencePhotosRepository(apiService: APIService, photosDao: PhotosDao): IPersistenceRepository<PhotosVO, PhotosEntity> =
        PersistencePhotosRepository(photosDao = photosDao, apiService = apiService)
}