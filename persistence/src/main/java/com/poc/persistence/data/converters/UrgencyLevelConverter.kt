package com.poc.persistence.data.converters

import androidx.room.TypeConverter
import com.poc.persistence.data.entities.UrgencyLevelVO

class UrgencyLevelConverter {
    // Convert to String, to save in database
    @TypeConverter
    fun fromUrgencyLevel(value: UrgencyLevelVO?): String? {
        return value?.name
    }
    // Convert to UrgencyLevel, to use in code
    @TypeConverter
    fun toUrgencyLevel(value: String?): UrgencyLevelVO? {
        return value?.let { UrgencyLevelVO.valueOf(it) }
    }
}