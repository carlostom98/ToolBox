package com.carams.cryptocurrency.presenter.viewintents.mainintents

sealed class MainIntent {
    data object FetchTodoTask : MainIntent()
}