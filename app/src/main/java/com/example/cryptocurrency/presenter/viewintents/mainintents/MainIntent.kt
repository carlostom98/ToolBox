package com.example.cryptocurrency.presenter.viewintents.mainintents

sealed class MainIntent {
    data object FetchTodoTask : MainIntent()
}