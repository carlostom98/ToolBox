package com.carams.cryptocurrency.presenter.viewintents.mainintents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carams.cryptocurrency.data.DataFromRemote
import com.carams.cryptocurrency.data.SuperHeroesDataBase
import com.carams.cryptocurrency.domain.interfaces.DataRepository
import com.carams.cryptocurrency.domain.RetrieveDataFromRemoteRepository
import com.carams.cryptocurrency.presenter.viewintents.ViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdaterViewModel @Inject constructor(): ViewModel() {
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
                    _mainState.value = ViewStates.LoadData(result)
                }, { error ->
                    _mainState.value = ViewStates.Error(error.message)
                })
        )
    }
}






