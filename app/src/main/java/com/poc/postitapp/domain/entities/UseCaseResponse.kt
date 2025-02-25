package com.poc.postitapp.domain.entities

sealed class UseCaseResponse {
    data object Success: UseCaseResponse()
    data object Error: UseCaseResponse()
}