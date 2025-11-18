package com.poc.toolboxapp.presenter.navigation

import com.poc.domain.entities.PhotosEntity
import kotlinx.serialization.Serializable


@Serializable
data object HomeScreenDestiny

@Serializable
data class DetailScreenDestiny(val photosEntity: PhotosEntity)
