package com.poc.persistence.data.entities

import kotlinx.serialization.SerialName

data class PhotosVO(
    @SerialName("albumId") val albumId: Int,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
    @SerialName("thumbNaiUrl") val thumbnailUrl: String
)
