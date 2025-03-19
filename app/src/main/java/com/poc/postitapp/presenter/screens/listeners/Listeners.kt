package com.poc.postitapp.presenter.screens.listeners

import androidx.compose.runtime.Composable
import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity

interface ListenViewState {
    @Composable
    fun OnSuccess(postIts: List<PostItEntity>)
    @Composable
    fun OnError(message: String)
    @Composable
    fun OnLoading()
}