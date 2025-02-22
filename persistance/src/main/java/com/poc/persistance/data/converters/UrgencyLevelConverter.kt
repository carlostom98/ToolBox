package com.poc.persistance.data.converters

import androidx.room.TypeConverter
import com.poc.persistance.domain.entities.UrgencyLevel

class UrgencyLevelConverter {
    // Convert to String, to save in database
    @TypeConverter
    fun fromUrgencyLevel(value: UrgencyLevel?): String? {
        return value?.name
    }
    // Convert to UrgencyLevel, to use in code
    @TypeConverter
    fun toUrgencyLevel(value: String?): UrgencyLevel? {
        return value?.let { UrgencyLevel.valueOf(it) }
    }
}