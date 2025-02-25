package com.poc.postitapp.domain.usecases.persistancecases

import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.data.persistance.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.utils.BaseUseCase

class AddPostItUseCase(private val repository: IRoomPersistenceRepository): BaseUseCase<PostItEntity, UseCaseResponse>() {
    override suspend fun doWork(parameter: PostItEntity): UseCaseResponse {
        return try {
            repository.insertOrUpdate(parameter)
            UseCaseResponse.Success
        } catch (e: Exception) {
            UseCaseResponse.Error
        }
    }

}