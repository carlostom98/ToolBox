package com.poc.viewmodel.viewintents.crudintent

import com.poc.domain.entities.PostItEntity
import com.poc.domain.entities.SortedBy


sealed class CRUDIntents {
    data class GetAllData(var sortedVy: SortedBy) : CRUDIntents()
    data class UpsertPostIt(val data: PostItEntity) : CRUDIntents()
    data class DeletePostIt(val data: PostItEntity) : CRUDIntents()
}