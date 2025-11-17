package com.poc.viewmodel.viewintents.crudintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.domain.entities.AlbumDetailedEntity
import com.poc.domain.entities.Response
import com.poc.domain.usecases.GetAlbumsDetailedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class GetAlbumsDetailsInfoViewModel @Inject constructor(private val getAlbumsDetailedUseCase: GetAlbumsDetailedUseCase) : ViewModel() {
    private val _response = MutableStateFlow<Response<List<AlbumDetailedEntity>>>(Response.Loading)
    val response get() = _response.asStateFlow()

    init {
        handleIntent(UserIntent.GetAllData)
    }

    fun handleIntent(userIntent: UserIntent) {
        when(userIntent) {
            UserIntent.GetAllData -> {
                getAlbumsDetailedUseCase(viewModelScope) {
                    _response.value = it
                }
            }
        }
    }

}