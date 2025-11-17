package com.poc.viewmodel.viewintents.createoreditintent

sealed class CreateOrEditIntent {
    data class AddTitle(val title: String) : CreateOrEditIntent()
    data class AddDescription(val description: String) : CreateOrEditIntent()
    data class SetColor(val color: Long) : CreateOrEditIntent()
    data class SetUrgencyLevel(val urgencyLevel: UrgencyLevel) : CreateOrEditIntent()
    data object resetPostItEntity : CreateOrEditIntent()
}