package com.poc.postitapp.presenter.screens.listeners

import androidx.compose.runtime.Composable

interface ListenViewState {
    @Composable
    fun OnSuccess(postIts: List<PostItEntity>)
    @Composable
    fun OnError(message: String)
    @Composable
    fun OnLoading()
}