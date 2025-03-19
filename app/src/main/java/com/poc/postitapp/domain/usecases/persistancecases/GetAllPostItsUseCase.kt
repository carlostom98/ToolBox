package com.poc.postitapp.domain.usecases.persistancecases

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import com.poc.postitapp.domain.interfaces.IRoomPersistenceRepository
import com.poc.postitapp.domain.entities.SortedBy
import com.poc.postitapp.domain.interfaces.BaseUseCaseMainThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostItsUseCase @Inject constructor (private val repository: IRoomPersistenceRepository): BaseUseCaseMainThread<SortedBy, Flow<List<PostItEntity>>>() {

    private val sortedBy = mapOf(
        SortedBy.TITLE to { getSortedByTitle() } ,
        SortedBy.DEFAULT to { getSortedByDefault() }
    )

    override fun doWork(parameter: SortedBy): Flow<List<PostItEntity>>  = sortedBy[parameter]?.invoke() ?: getSortedByDefault()


    private fun getSortedByTitle() = repository.getAllSortedByTitle()
    private fun getSortedByDefault() = repository.getAllSortedByTitle()

}