package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.data.retrofit.CountriesService
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.utils.BaseUseCaseNoParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.system.measureTimeMillis

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

//fun main(array: Array<String>) {
//    runBlocking {
//        tryCatchExceptions()
//    }
//}

//suspend fun tryCatchExceptions() {
//    (1..4).asFlow()
//        .onEach { check(it != 6) }
//        .onCompletion { completion ->
//            if (completion != null)
//                println("Flow completed with exception: ${completion.message}")
//            else {
//                println("Flow Completed successfully")
//            }
//        }
//        .catch { e -> println("Exception caught: ${e.message}") }
//        .collect {
//            println(it)
//        }
//
//}
//
//suspend fun generateFlowA() = flow {
//    for (i in 1..3) {
//        delay(300L)
//        emit(i)
//    }
//}
//
//suspend fun generateFlowB() = flow {
//    for (i in 4..10) {
//        delay(200L)
//        emit(i)
//    }
//}
//
//
//suspend fun transform(a: Int, b: Int): String {
//    return "flow a: $a, flow b: $b"
//}
//
//fun printValues(value: Any) {
//    println("The value is: $value")
//}
//
//suspend fun factorial(value: Int) = (1..value).asFlow().flowOn(Dispatchers.IO).reduce { accumulator, value ->
//    println("accumulator: $accumulator and current value: $value, result: ${accumulator * value}")
//    accumulator * value
//}