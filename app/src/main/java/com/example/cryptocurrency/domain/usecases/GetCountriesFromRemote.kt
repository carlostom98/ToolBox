package com.example.cryptocurrency.domain.usecases

import com.example.cryptocurrency.data.retrofit.CountriesService
import com.example.cryptocurrency.domain.entities.CountriesEntity
import com.example.cryptocurrency.utils.BaseUseCaseNoParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

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
        // Capacity is reached the sender is paused
        val channel = Channel<Int>(capacity = 5)
        val sender = launch {
            repeat(10) {
                channel.send(it)
                println("Sent: $it")
            }
        }

        repeat(3) {
            delay(1000L)
            println("Received value : ${channel.receive()}")
        }

        sender.cancel()
    }
}

suspend fun sendString(channel: SendChannel<String>, time: Long, s: String) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}


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