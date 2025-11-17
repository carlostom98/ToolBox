package com.poc.persistence.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poc.domain.entities.PhotosEntity
import kotlinx.serialization.SerialName

@Entity("photos")
data class PhotosVO(
    @PrimaryKey
    @SerialName("id") val id: Int,
    @SerialName("albumId") val albumId: Int,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
    @SerialName("thumbNaiUrl") val thumbnailUrl: String
) {
    fun voToPhotosEntity() = with(this) {
        PhotosEntity(
            albumId = albumId,
            title = title,
            url = url
        )
    }
}
