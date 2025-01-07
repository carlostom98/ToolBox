package com.example.cryptocurrency.data

import android.util.Log
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.random.Random

abstract class BaseClass<in Params, out ResultType> where ResultType : Any {

    private val backGround = Dispatchers.IO
    private val foreGround = Dispatchers.Main

    protected abstract fun doWork(params: Params): Result<ResultType>
    open operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        onResult: (Result<ResultType>) -> Unit
    ) {
        val pending = scope.async(Dispatchers.IO) { doWork(params) }
        scope.launch(Dispatchers.Main) { onResult(pending.await()) }
    }
}














