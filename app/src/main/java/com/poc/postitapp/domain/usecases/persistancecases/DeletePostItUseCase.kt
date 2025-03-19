package com.poc.postitapp.domain.usecases.persistancecases

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.domain.interfaces.BaseUseCase
import javax.inject.Inject

class DeletePostItUseCase @Inject constructor (private val repository: IRoomPersistenceRepository): BaseUseCase<PostItEntity, UseCaseResponse>() {
    override suspend fun doWork(parameter: PostItEntity): UseCaseResponse {
        return try {
            repository.delete(parameter)
            UseCaseResponse.Success
        } catch (e: Exception) {
            UseCaseResponse.Error
        }
    }
}