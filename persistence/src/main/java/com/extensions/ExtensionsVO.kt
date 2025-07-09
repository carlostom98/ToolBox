package com.extensions

import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.persistence.data.entitiesdb.UrgencyLevel
import com.poc.postitapp.domain.entities.PostItEntity

fun PostItVO.toPostItEntity() = PostItEntity().copy(
    id = this.id,
    title = this.title,
    description = this.description,
    color = this.color,
    urgencyLevel = this.urgencyLevel
)

fun UrgencyLevelVo.t