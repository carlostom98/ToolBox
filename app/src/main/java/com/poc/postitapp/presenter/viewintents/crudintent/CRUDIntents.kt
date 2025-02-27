package com.poc.postitapp.presenter.viewintents.crudintent

import com.poc.persistence.domain.entities.PostItEntity

sealed class CRUDIntents {
    data object GetAllData : CRUDIntents()
    data object GetAllDataSorted : CRUDIntents()
    data class UpsertPostIt(val data: PostItEntity) : CRUDIntents()
    data class DeletePostIt(val data: PostItEntity) : CRUDIntents()
}