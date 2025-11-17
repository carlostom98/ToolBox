package com.poc.dimodule.di.domain


import com.poc.data.interfaces.IPersistenceRepository
import com.poc.domain.entities.AlbumEntity
import com.poc.domain.entities.PhotosEntity
import com.poc.domain.usecases.GetAlbumsDetailedEntityList
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object DomainModule {
    @Provides
    fun provideGetAllPostItUseCase(repositoryPhotos: IPersistenceRepository<PhotosEntity>,
                                   repositoryAlbums: IPersistenceRepository<AlbumEntity>)  = GetAlbumsDetailedEntityList(repositoryPhotos, repositoryAlbums)

}