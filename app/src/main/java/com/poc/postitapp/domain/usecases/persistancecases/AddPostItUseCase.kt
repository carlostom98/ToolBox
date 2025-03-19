package com.poc.postitapp.domain.usecases.persistancecases

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.domain.interfaces.BaseUseCase
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