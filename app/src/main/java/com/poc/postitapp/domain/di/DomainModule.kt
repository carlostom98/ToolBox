package com.poc.postitapp.domain.di

import com.poc.postitapp.data.persistance.IRoomPersistenceRepository
import com.poc.postitapp.domain.usecases.UseCases
import com.poc.postitapp.domain.usecases.persistancecases.AddPostItUseCase
import com.poc.postitapp.domain.usecases.persistancecases.DeletePostItUseCase
import com.poc.postitapp.domain.usecases.persistancecases.GetAllPostItsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
object DomainModule {

    @Provides
    fun provideDeletePostItUseCase(roomPersistenceRepository: IRoomPersistenceRepository): DeletePostItUseCase =
        DeletePostItUseCase(roomPersistenceRepository)

    @Provides
    fun provideGetAllPostItUseCase(roomPersistenceRepository: IRoomPersistenceRepository): GetAllPostItsUseCase =
        GetAllPostItsUseCase(roomPersistenceRepository)

    @Provides
    fun provideAddPosItUseCase(roomPersistenceRepository: IRoomPersistenceRepository): AddPostItUseCase =
        AddPostItUseCase(roomPersistenceRepository)

    @Provides
    fun getUseCases(
        deletePostItUseCase: DeletePostItUseCase,
        getAllPostItsUseCase: GetAllPostItsUseCase,
        addPostItUseCase: AddPostItUseCase
    ) = UseCases(deletePostItUseCase, getAllPostItsUseCase, addPostItUseCase)


}