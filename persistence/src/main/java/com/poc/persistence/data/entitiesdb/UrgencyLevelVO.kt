package com.poc.persistence.data.entitiesdb

import kotlinx.serialization.Serializable

@Serializable
enum class UrgencyLevelVO {
    HIGH,
    MEDIUM,
    LOW
}