package com.poc.postitapp.presenter.viewintents

sealed class ViewStates  () {
    data object Loading: ViewStates()
    data class LoadData <T> (val data: T?): ViewStates()
    data class Error(val errorMessage: String?) : ViewStates()
}