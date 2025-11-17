package com.poc.domain.entities




data class AlbumDetailedEntity(
    val title: String,
    val id: Int,
    val photos: List<PhotosEntity>
)