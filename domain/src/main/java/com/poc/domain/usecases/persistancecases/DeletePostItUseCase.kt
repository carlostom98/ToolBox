package com.poc.domain.usecases.persistancecases

import com.poc.domain.entities.Response
import com.poc.domain.interfaces.IRoomPersistenceRepository
import javax.inject.Inject

class DeletePostItUseCase @Inject constructor (private val repository: IRoomPersistenceRepository) {
    suspend operator fun invoke(postItEntity: PostItEntity): Response<Nothing> {
        return try {
            repository.delete(postItEntity)
            Response.Success()
        } catch (e: Exception) {
            Response.Error
        }
    }
}