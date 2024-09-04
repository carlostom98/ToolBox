package com.example.cryptocurrency.data

import android.util.Log
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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


// View
class View(val presenter: IPresenter) : IView {

    fun onClick1() {
        presenter.readData()
    }

    fun addData() {
        val random = Random.nextInt(10000).toString()
        presenter.addData(random)
    }

    fun <T> enqueue(data: T) {
        when (data) {
            is Int -> println("Int")
            is Boolean -> println("Boolean")
            is String -> println("String")
        }
    }

    override fun showProgress() {
        println("... and Process showing")
    }

    override fun showImage() {
        println("... and Image showing")
    }

}

interface IView {
    fun showProgress()
    fun showImage()
}

// Presenter
class Presenter() : IPresenter {

    private val view: IView = View(this)
    private val repository: IRepository = ReadDataFromRetrofit(this)

    override fun showResult(list: List<String>) {
        list.forEach {
            println(it)
        }
        view.showProgress()
    }

    override fun readData() {
        repository.readData()
    }

    override fun addData(drink: String) {
        repository.addData(drink)
    }

    override fun showImage() {
        view.showImage()
    }

}


interface IPresenter {
    fun showResult(list: List<String>)
    fun readData()
    fun addData(drink: String)
    fun showImage()
}

// Model

class ReadDataFromRetrofit(val presenter: IPresenter) : IRepository {
    private val listOfDrinks = mutableListOf<String>()
    override fun readData() {
        if (listOfDrinks.isNotEmpty()) {
            presenter.showResult(listOfDrinks)
        } else {
            println("Cannot be empty")
        }
    }

    override fun addData(drink: String): Boolean {
        listOfDrinks.add(drink)
        return true
    }
}

interface IRepository {
    fun readData()
    fun addData(drink: String): Boolean
}


fun something2(block: (number: Int) -> Int): Int {
    return block(5)
}

//fun main(array: Array<String>) {
//
////    val string = "Hola".something {
////        it +"....."+ "Aca es donde se devuelve el string"
////    }
////
////    val result = something2 { number ->
////        number + 10 +15
////    }
////
////    println(string)
//
//    getPairs( "Hello" to 2)
//
//
//}

fun <T, R> T.something(block: (T) -> R): R {
    return block(this)
}

sealed class NotesType(val orderType: OrderType) {
    class Title(orderType: OrderType) : NotesType(orderType)
    class Date(orderType: OrderType) : NotesType(orderType)
    class Views(orderType: OrderType) : NotesType(orderType)

    fun copy(orderType: OrderType): NotesType {
        return when (this) {
            is Date -> Date(orderType)
            is Title -> Title(orderType)
            is Views -> Views(orderType)
        }
    }
}

fun printType(notesType: NotesType) {
    when (notesType.orderType) {
        OrderType.Ascendant -> {
            when (notesType) {
                is NotesType.Date -> println("Asc, Date")
                is NotesType.Title -> println("Asc, Title")
                is NotesType.Views -> println("Asc, Views")
            }
        }

        OrderType.Descendant -> {
            when(notesType) {
                is NotesType.Date -> println("Dsc, Date")
                is NotesType.Title -> println("Dsc, Title")
                is NotesType.Views -> println("Dsc, Views")
            }
        }
    }
}

fun getPairs(scan: Pair<String, Int>) {
    println("first one ${scan.first},  secondo one ${scan.second}")
}


sealed class OrderType() {
    object Ascendant : OrderType()
    object Descendant : OrderType()
}









