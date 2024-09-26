package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.data.retrofit.CountriesService
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.utils.BaseUseCaseNoParams
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class GetCountriesFromRemote : BaseUseCaseNoParams<Result<List<CountriesEntity>>>() {

    override suspend fun doWork(): Result<List<CountriesEntity>> {
        val response = CountriesService.getService().getCountries()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable("Something went wrong with Retrofit"))
        }
    }
}

fun main(array: Array<String>) {
    runBlocking {
        val numbers = sendValues()
        println("Flow is about to start")
        println("Starting flow:  ")

        println(numbers)
    }
}

fun printValues(value: Any) {
    println("The value is: $value")
}

suspend fun sendValues() = (1..10).asFlow().reduce { accumulator, value ->
    println("accumulator: $accumulator and current value: $value, result: ${accumulator * value}")
    accumulator * value
}