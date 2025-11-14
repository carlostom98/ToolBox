package com.poc.persistence.data.entities

import kotlinx.serialization.SerialName


data class AlbumVO (
    @SerialName("userId") val userId: Int,
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String)

