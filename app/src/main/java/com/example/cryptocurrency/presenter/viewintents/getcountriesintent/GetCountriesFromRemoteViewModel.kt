package com.example.cryptocurrency.presenter.viewintents.getcountriesintent

import androidx.lifecycle.ViewModel
import com.example.cryptocurrency.domain.usecases.GetCountriesFromRemote
import com.example.cryptocurrency.domain.usecases.UseCases
import kotlinx.coroutines.channels.Channel

class GetCountriesFromRemoteViewModel: ViewModel() {
    private val getImages: UseCases = UseCases(
        getCountriesFromRemote = GetCountriesFromRemote()
    )

    val userIntent = Channel<>

}