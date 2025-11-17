package com.poc.persistence.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.poc.domain.entities.AlbumEntity
import kotlinx.serialization.SerialName

@Entity("albums")
data class AlbumVO (
    @PrimaryKey
    @SerialName("id") val id: Int,
    @SerialName("userId") val userId: Int,
    @SerialName("title") val title: String) {

    fun voToAlbumEntity() = with(this) {
        AlbumEntity(
            userId = userId,
            id = id,
            title = title
        )
    }

    fun albumEntityToVo(albumEntity: AlbumEntity) = AlbumVO(
        userId = albumEntity.userId,
        id = albumEntity.id,
        title = albumEntity.title
    )
}

