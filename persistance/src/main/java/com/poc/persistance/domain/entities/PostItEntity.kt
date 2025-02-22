package com.poc.persistance.domain.entities

data class PostItEntity (
    val id: Int ?,
    val title: String ?,
    val description: String ?,
    val color: String ?,
    val urgencyLevel: UrgencyLevel ?
)