package com.poc.persistence.data.entitiesdb

import kotlinx.serialization.Serializable

@Serializable
enum class UrgencyLevel {
    HIGH,
    MEDIUM,
    LOW
}