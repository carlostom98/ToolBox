package com.poc.domain.usecases.persistancecases

import com.poc.domain.entities.PostItEntity
import com.poc.domain.entities.SortedBy
import com.poc.domain.entities.UseCaseResponse
import com.poc.domain.interfaces.IRoomPersistenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostItsUseCase @Inject constructor (private val repository: IRoomPersistenceRepository) {

    private val sortedBy = mapOf(
        SortedBy.TITLE to { getSortedByTitle() } ,
        SortedBy.DEFAULT to { getSortedByDefault() }
    )

    operator fun invoke(parameter: SortedBy): UseCaseResponse<Flow<List<PostItEntity>>>  {
       return try {
            val result = sortedBy[parameter]?.invoke() ?: getSortedByDefault()
            UseCaseResponse.Success(result)
        } catch (error: Throwable) {
            throw error
        }
    }


    private fun getSortedByTitle() = repository.getAllSortedByTitle()
    private fun getSortedByDefault() = repository.getAll()

}