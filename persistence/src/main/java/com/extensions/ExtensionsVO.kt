package com.extensions

import com.poc.domain.entities.PostItEntity
import com.poc.domain.entities.UrgencyLevel
import com.poc.persistence.data.entitiesdb.PostItVO
import com.poc.persistence.data.entitiesdb.UrgencyLevelVO


fun PostItVO.toPostItEntity() = PostItEntity().copy(
    id = this.id,
    title = this.title,
    description = this.description,
    color = this.color,
    urgencyLevel = this.urgencyLevelVo?.toUrgencyLevel()
)

fun PostItEntity.toPostItVo() = PostItVO(
    id = this.id,
    title = this.title,
    description = this.description,
    color = this.color,
    urgencyLevelVo = this.urgencyLevel?.toUrgencyLevelVo()
)

fun UrgencyLevelVO.toUrgencyLevel() = when (this) {
    UrgencyLevelVO.HIGH -> UrgencyLevel.HIGH
    UrgencyLevelVO.MEDIUM -> UrgencyLevel.MEDIUM
    UrgencyLevelVO.LOW -> UrgencyLevel.LOW
}

fun UrgencyLevel.toUrgencyLevelVo() = when (this) {
    UrgencyLevel.HIGH -> UrgencyLevelVO.HIGH
    UrgencyLevel.MEDIUM -> UrgencyLevelVO.MEDIUM
    UrgencyLevel.LOW -> UrgencyLevelVO.LOW
}