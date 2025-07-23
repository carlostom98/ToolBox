package com.poc.domain.usecases.persistancecases

import com.poc.domain.entities.PostItEntity
import com.poc.domain.entities.UseCaseResponse
import com.poc.domain.interfaces.IRoomPersistenceRepository
import javax.inject.Inject

class AddPostItUseCase @Inject constructor (private val repository: IRoomPersistenceRepository) {
    suspend operator fun invoke(postItEntity: PostItEntity): UseCaseResponse<Boolean> {
        return try {
            if (postItEntity.color != null) {
                repository.insertOrUpdate(postItEntity)
            } else {
                val auxPostIt = postItEntity.copy(color = 0xFFFFFF)
                repository.insertOrUpdate(auxPostIt)
            }
            UseCaseResponse.Success(true)
        } catch (e: Exception) {
            UseCaseResponse.Error
        }
    }

}