package com.example.cryptocurrency.presenter.viewintents

sealed class ViewStates  () {
    data object Idle: ViewStates()
    data object Loading: ViewStates()
    data class LoadData <T> (val data: T): ViewStates()
    data class Error(val errorMessage: String?) : ViewStates()
}