package com.poc.domain.entities

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class PostItEntity(val id: Int = 0,
                        val title: String ? = null,
                        val description: String ? = null,
                        val color: Long ? = null,
                        val urgencyLevel: UrgencyLevel? = null)



