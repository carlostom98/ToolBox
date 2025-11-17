package com.poc.domain.usecases.persistancecases

import com.poc.domain.entities.Response
import com.poc.domain.interfaces.IRoomPersistenceRepository
import javax.inject.Inject

class AddPostItUseCase @Inject constructor (private val repository: IRoomPersistenceRepository) {
    suspend operator fun invoke(postItEntity: PostItEntity): Response<Boolean> {
        return try {
            if (postItEntity.color != null) {
                repository.insertOrUpdate(postItEntity)
            } else {
                val auxPostIt = postItEntity.copy(color = 0xFFFFFF)
                repository.insertOrUpdate(auxPostIt)
            }
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error
        }
    }

}