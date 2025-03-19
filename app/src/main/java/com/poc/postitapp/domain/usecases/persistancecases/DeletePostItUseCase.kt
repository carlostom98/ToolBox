package com.poc.postitapp.domain.usecases.persistancecases

import androidx.annotation.Nullable
import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.domain.interfaces.BaseUseCase
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