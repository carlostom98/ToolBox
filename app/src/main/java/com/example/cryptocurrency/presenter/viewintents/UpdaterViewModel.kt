package com.example.cryptocurrency.presenter.viewintents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.data.DataFromRemote
import com.example.cryptocurrency.data.SuperHeroesDataBase
import com.example.cryptocurrency.domain.interfaces.DataRepository
import com.example.cryptocurrency.domain.RetrieveDataFromRemoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UpdaterViewModel : ViewModel() {
    private var dataRepository: DataRepository? = null
    private var dataFromRemote: DataFromRemote? = null

    private val disposable = CompositeDisposable()

    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<ViewStates>(ViewStates.Idle)

    val mainState: StateFlow<ViewStates> get() = _mainState

    init {
        dataFromRemote = SuperHeroesDataBase()
        dataFromRemote?.let {
            dataRepository = RetrieveDataFromRemoteRepository(it)
        }
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    MainIntent.FetchTodoTask -> retrieveData()
                }
            }
        }
    }

    private fun retrieveData() {
        disposable.add(
            dataRepository?.getFromRemote()
            !!.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    _mainState.value = ViewStates.LoadSuperheroes(result)
                }, { error ->
                    _mainState.value = ViewStates.Error(error.message)
                })
        )
    }
}
//
//fun main(array: Array<String>) {
//    val listOfSomeData = listOf(
//        SomeData(true, "Carlos"),
//        SomeData(false, "Carlosss"),
//        SomeData(true, "Andrés")
//    )
//
//    val functionReference: (string: SomeData) -> Boolean = { string -> string.name == "Carlos" }
//
//    val data = listOfSomeData.filter(functionReference)
//
//    println(data[0].name)
//}
//
//fun dataClassTesting(string: SomeData) = string.name == "Carlos"
//
//data class SomeData(val isApproved: Boolean, val name: String)

//
//fun functionRandom(isSelected: Boolean, presenter: (User) -> Boolean) {
//    val listOfUsers = listOf(
//        User("carlos", "2", 15, 2, "HIGH"),
//        User("hola", "3", 22, 5, "HIGH"),
//        User("marcela", "8", 10, 4, "HIGH"),
//        User("gringo", "6", 18, 3, "HIGH"),
//        )
//
//
//}
//
//
//
//
//data class User(
//    val name: String,
//    val lastName: String,
//    val age: Int,
//    val id: Int,
//    val socialStatus: String
//)
//
//data class UserView(
//    val namePrefix: String,
//    val lastNamePrefix: String,
//    val ageMonths: Int,
//    val socialStatusDefined: String
//)
//
//fun createUser(): User {
//    return User(name = "Carlos", lastName = "Martinez", age = 25, id = 1020, socialStatus = "6")
//}
//
//fun User.toUserViewReflection() = with(::UserView) {
//
//}
//
//class Apples(val radious: Double = 2.0) {
//    fun news() {
//        println("This is a healthy fruit!!! eat apples".trimIndent())
//    }
//}











