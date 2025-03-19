package com.poc.persistence.data.entitiesdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "PostIt")
data class PostItVO (
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
   @ColumnInfo (name = "title") val title: String ?,
   @ColumnInfo (name = "description") val description: String ?,
   @ColumnInfo (name = "color") val color: Long ?,
   @ColumnInfo (name = "urgencyLevel") val urgencyLevel: UrgencyLevel?
)