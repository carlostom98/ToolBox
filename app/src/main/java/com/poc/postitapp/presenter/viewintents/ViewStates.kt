package com.poc.postitapp.presenter.viewintents

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity

sealed class ViewStates  () {
    data object Loading: ViewStates()
    data class LoadData (val data: ViewModelResponse): ViewStates()
    data class Error(val errorMessage: String?) : ViewStates()
}

data class ViewModelResponse(
    val postItsList: List<PostItEntity>?,
    val successfullyResponse: Boolean?
)