package com.example.cryptocurrency.domain.usecases

import javax.inject.Inject

data class UseCases @Inject constructor(
    val getOriginalImage: GetOriginalImage,
    val getProcessedImage: GetProcessedImage,
    val getCountriesFromRemote: GetCountriesFromRemote,
)
