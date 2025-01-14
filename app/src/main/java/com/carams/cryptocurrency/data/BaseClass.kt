package com.carams.cryptocurrency.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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














