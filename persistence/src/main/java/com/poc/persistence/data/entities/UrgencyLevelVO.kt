package com.poc.persistence.data.entities

import kotlinx.serialization.Serializable

@Serializable
enum class UrgencyLevelVO {
    HIGH,
    MEDIUM,
    LOW
}