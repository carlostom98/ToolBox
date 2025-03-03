package com.poc.persistence.domain.entities

import kotlinx.serialization.Serializable

@Serializable
enum class UrgencyLevel {
    HIGH,
    MEDIUM,
    LOW
}