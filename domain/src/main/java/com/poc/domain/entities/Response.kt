package com.poc.domain.entities


sealed class Response <out T> {
    data class Success <T> (val response: T ? = null): Response<T>()
    data class Error(val errorMessage: String): Response<Nothing>()

    data object Loading: Response<Nothing>()
}