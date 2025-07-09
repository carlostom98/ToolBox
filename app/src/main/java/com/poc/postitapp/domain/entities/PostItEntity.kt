package com.poc.postitapp.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class PostItEntity(val id: Int = 0,
                        val title: String ? = null,
                        val description: String ? = null,
                        val color: Long ? = null,
                        val urgencyLevel: UrgencyLevel? = null)



