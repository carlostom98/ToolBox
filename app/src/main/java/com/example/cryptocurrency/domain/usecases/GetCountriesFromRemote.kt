package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.data.retrofit.CountriesService
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.utils.BaseUseCaseNoParams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class GetCountriesFromRemote @Inject constructor (): BaseUseCaseNoParams<Result<List<CountriesEntity>>>() {

    override suspend fun doWork(): Result<List<CountriesEntity>> {
        val response = CountriesService.getService().getCountries()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Throwable("Something went wrong with Retrofit"))
        }
    }
}

//fun CoroutineScope.squareValues(received: ReceiveChannel<Int>) = produce {
//    for (i in received) {
//        send(i * i)
//    }
//}
//
//suspend fun sendString(channel: SendChannel<Int>, time: Long, s: Int) {
//    while (true) {
//        delay(time)
//        channel.send(s)
//        println("Sent: $s")
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