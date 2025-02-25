package com.poc.postitapp.domain.usecases.persistancecases

import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.data.persistance.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.SortedBy
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.utils.BaseUseCase
import com.poc.postitapp.utils.BaseUseCaseMainThread
import kotlinx.coroutines.flow.Flow

class GetAllPostItsUseCase(private val repository: IRoomPersistenceRepository): BaseUseCaseMainThread<SortedBy, Flow<List<PostItEntity>>>() {

    private val sortedBy = mapOf(
        SortedBy.TITLE to { getSortedByTitle() } ,
        SortedBy.DEFAULT to { getSortedByDefault() }
    )

    override fun doWork(parameter: SortedBy): Flow<List<PostItEntity>>  = sortedBy[parameter]?.invoke() ?: getSortedByDefault()


    private fun getSortedByTitle() = repository.getAllSortedByTitle()
    private fun getSortedByDefault() = repository.getAllSortedByTitle()

}