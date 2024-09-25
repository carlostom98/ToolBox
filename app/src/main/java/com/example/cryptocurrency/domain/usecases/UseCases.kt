package com.example.cryptocurrency.domain.usecases

data class UseCases(
    val getOriginalImage: GetOriginalImage? = null,
    val getProcessedImage: GetProcessedImage? = null,
    val getCountriesFromRemote: GetCountriesFromRemote? = null,
)
