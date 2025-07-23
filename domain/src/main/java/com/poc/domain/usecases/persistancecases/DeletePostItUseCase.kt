package com.poc.domain.usecases.persistancecases

import com.poc.domain.entities.PostItEntity
import com.poc.domain.entities.UseCaseResponse
import com.poc.domain.interfaces.IRoomPersistenceRepository
import javax.inject.Inject

class DeletePostItUseCase @Inject constructor (private val repository: IRoomPersistenceRepository) {
    suspend operator fun invoke(postItEntity: PostItEntity): UseCaseResponse<Nothing> {
        return try {
            repository.delete(postItEntity)
            UseCaseResponse.Success()
        } catch (e: Exception) {
            UseCaseResponse.Error
        }
    }
}