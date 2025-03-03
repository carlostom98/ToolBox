package com.poc.persistence.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "PostIt")
data class PostItEntity (
   @PrimaryKey(autoGenerate = true) val id: Int = 0,
   @ColumnInfo (name = "title") val title: String ?,
   @ColumnInfo (name = "description") val description: String ?,
   @ColumnInfo (name = "color") val color: String ?,
   @ColumnInfo (name = "urgencyLevel") val urgencyLevel: UrgencyLevel ?
)