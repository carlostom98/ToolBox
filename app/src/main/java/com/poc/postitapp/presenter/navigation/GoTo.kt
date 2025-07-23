package com.poc.postitapp.presenter.navigation

import com.poc.domain.entities.PostItEntity
import kotlinx.serialization.Serializable


@Serializable
data object PostItScreen

@Serializable
data class DetailScreen(val postItEntity: PostItEntity)

@Serializable
data object CreatePostItScreen