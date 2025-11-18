package com.poc.domain.entities

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class PhotosEntity(
    val albumId: Int,
    val title: String,
    val url: String
)