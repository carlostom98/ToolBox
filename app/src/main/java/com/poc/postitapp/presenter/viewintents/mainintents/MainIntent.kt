package com.poc.postitapp.presenter.viewintents.mainintents

sealed class MainIntent {
    data object FetchTodoTask : MainIntent()
}