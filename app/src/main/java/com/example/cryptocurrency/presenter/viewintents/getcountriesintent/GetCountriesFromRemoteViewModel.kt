package com.example.cryptocurrency.presenter.viewintents.getcountriesintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.usecases.GetCountriesFromRemote
import com.example.cryptocurrency.domain.usecases.UseCases
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class GetCountriesFromRemoteViewModel: ViewModel() {
    private val getImages: UseCases = UseCases(
        getCountriesFromRemote = GetCountriesFromRemote()
    )

    val userIntent = Channel<CountriesIntents>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<ViewStates>(ViewStates.Loading)
    val mainState get() = _mainState.asStateFlow()

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { intent ->
                when(intent) {
                    CountriesIntents.GetData -> getFromRemote()
                }
            }
        }
    }

    private fun getFromRemote() {
        _mainState.value = ViewStates.Loading
        getImages.getCountriesFromRemote!!(viewModelScope) { countriesResult ->
            countriesResult.fold(
                onSuccess = { countriesList ->
                    _mainState.value = ViewStates.LoadData(countriesList)
                },
                onFailure = { error ->
                    _mainState.value = ViewStates.Error(error.message)
                    Log.e("RETROFIT", "Error: ${error.message}")
                }
            )
        }
    }

}