package com.poc.postitapp.domain.entities

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.persistence.data.entitiesdb.UrgencyLevel
import kotlinx.serialization.Serializable

@Serializable
data class PostItEntity(val id: Int = 0,
                        val title: String ? = null,
                        val description: String ? = null,
                        val color: Long ? = null,
                        val urgencyLevel: UrgencyLevel? = null) {

    fun mapFromVOToEntity(postItVO: PostItVO): PostItEntity {
        return this.copy(
            id = postItVO.id,
            title = postItVO.title,
            description = postItVO.description,
            color = postItVO.color,
            urgencyLevel = postItVO.urgencyLevel
        )
    }

    fun mapFromEntityToVo(): PostItVO {
        return with(this) {
            PostItVO(
                id = id,
                title = title,
                description = description,
                color = color,
                urgencyLevel = urgencyLevel
            )
        }
    }
}



