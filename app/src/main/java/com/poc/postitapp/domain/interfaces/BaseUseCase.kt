package com.poc.postitapp.domain.interfaces

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<in Parameter, out Output> where Output : Any {

    private val coroutineScopeMain = CoroutineScope(Dispatchers.Main)

    abstract suspend fun doWork(parameter: Parameter): Output

    operator fun invoke(
        coroutineScope: CoroutineScope,
        parameter: Parameter,
        onDataRetrieved: (Output) -> Unit
    ) {
        val deferred = coroutineScope.async { doWork(parameter) }
        coroutineScopeMain.launch { onDataRetrieved(deferred.await()) }
    }
}

abstract class BaseUseCaseMainThread<in Parameter, out Output> where Output : Any {

    abstract fun doWork(parameter: Parameter): Output

    operator fun invoke(
        parameter: Parameter
    ): Output = doWork(parameter)

}

abstract class BaseUseCaseNoParams<out Output> where Output: Any {
    private val coroutineScopeMain = CoroutineScope(Dispatchers.Main)

    abstract suspend fun doWork(): Output

    operator fun invoke(
        coroutineScope: CoroutineScope,
        onDataRetrieved: (Output) -> Unit
    ) {
        val deferred = coroutineScope.async { doWork() }
        coroutineScopeMain.launch { onDataRetrieved(deferred.await()) }
    }
}