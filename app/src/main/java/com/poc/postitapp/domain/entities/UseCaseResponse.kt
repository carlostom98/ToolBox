package com.poc.postitapp.domain.entities

sealed class UseCaseResponse <out T> {
    data class Success <T> (val response: T ? = null): UseCaseResponse<T>()
    data object Error: UseCaseResponse<Nothing>()
}