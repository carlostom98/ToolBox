package com.poc.postitapp.presenter.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.poc.persistence.domain.entities.PostItEntity
import com.poc.postitapp.presenter.navigation.custom.CustomNavTypePostIt
import kotlinx.serialization.Serializable



@Serializable
data object PostItScreen

@Serializable
data class DetailScreen(val postItEntity: PostItEntity)

@Serializable
data object CreatePostItScreen