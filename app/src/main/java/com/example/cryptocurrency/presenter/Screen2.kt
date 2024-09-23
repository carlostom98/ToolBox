package com.example.cryptocurrency.presenter

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.FragmentScreen2Binding
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random


class Screen2 : Fragment() {

    private lateinit var _binding: FragmentScreen2Binding
    private val binding: FragmentScreen2Binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentScreen2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            binding.textEdit.setOnKeyListener { view, keyCode, keyEvent ->
                Log.d("ONKEY", "${keyEvent.action}, code: ${keyCode.dec()}")
                true
            }
        }
    }
}

fun main(array: Array<String>) {
    runBlocking {

        val myHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            println("Exception handled: ${throwable.localizedMessage}")
        }

        val job = GlobalScope.launch(myHandler) {
            println("Throwing exception from job")
            throw IndexOutOfBoundsException("Out of bounds")
        }

        job.join()

        val deferred = GlobalScope.async {
            println("Throwing exception from async")
            delay(2000)
            1/0
        }
        try {
            deferred.await()
        } catch (e: ArithmeticException) {
            println("Exception: ${e.message}")
        }
    }
}

suspend fun getRandom1(): Int {
    delay(3000L)
    return Random.nextInt(1000)
}

suspend fun getRandom2(): Int {
    delay(2000L)
    return Random.nextInt(1000)
}