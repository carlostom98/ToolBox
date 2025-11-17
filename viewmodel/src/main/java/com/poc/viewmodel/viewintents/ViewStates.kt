package com.poc.viewmodel.viewintents


sealed class ViewStates  () {
    data object Loading: ViewStates()
    data class LoadData (val data: ViewModelResponse): ViewStates()
    data class Error(val errorMessage: String?) : ViewStates()
}

data class ViewModelResponse(
    val postItsList: List<PostItEntity>?,
    val successfullyResponse: Boolean?
)