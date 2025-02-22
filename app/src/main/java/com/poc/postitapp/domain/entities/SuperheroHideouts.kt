package com.poc.postitapp.domain.entities

import com.poc.postitapp.domain.interfaces.AdapterItems
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

data class SuperheroHideouts(
    val name: String,
    val type: String,
    val superHero: SuperHeroData? = null
) : AdapterItems {
    override fun isItemTheSame(other: AdapterItems): Boolean {
        return (other as? SuperheroHideouts)?.name == this.name
    }

    override fun isContentTheSame(other: AdapterItems): Boolean {
        return (other as? SuperheroHideouts) == this
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main(array: Array<String>) {
    runBlocking {
        val job1 = GlobalScope.launch {
            delay(3000L)
            println("GlobalScope call")
            launch {
                val job2 = launch(CoroutineName("myCoroutine")) {
                    runSomethingInTheBackground(coroutineContext)
                }
            }
        }

        job1.invokeOnCompletion { println("Finished job1") }
        delay(4000L)
        println("Job1 is about to be cancelled")
        job1.cancel()
    }
    println("All coroutines in coroutineScope have completed.")
}

suspend fun runSomethingInTheBackground(context: CoroutineContext) {
    delay(1000L)
    println("Running from function, name: ${context[CoroutineName.Key]}")
}

