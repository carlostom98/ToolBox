package com.poc.postitapp.presenter.navigation

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.postitapp.domain.entities.PostItEntity
import kotlinx.serialization.Serializable



@Serializable
data object PostItScreen

@Serializable
data class DetailScreen(val postItEntity: PostItEntity)

@Serializable
data object CreatePostItScreen