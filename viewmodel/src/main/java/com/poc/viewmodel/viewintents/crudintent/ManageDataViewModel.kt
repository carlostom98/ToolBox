package com.poc.viewmodel.viewintents.crudintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.domain.entities.Response
import com.poc.domain.usecases.UseCases
import com.poc.viewmodel.viewintents.ViewModelResponse
import com.poc.viewmodel.viewintents.ViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ManageDataViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _sortedBy = MutableStateFlow(SortedBy.DEFAULT)

    private val _postItsSortedBy = _sortedBy.flatMapLatest { sortedBy ->
        useCases.getAllPostItsUseCase(sortedBy).let {
            if (it is Response.Success<Flow<List<PostItEntity>>>) it.response else emptyFlow()
            } ?: emptyFlow()
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isSuccessfulResponse = MutableStateFlow(false)

    val mainState = _postItsSortedBy.combine(_isSuccessfulResponse) { postItSortedBy, isSuccessFullResponse ->
            ViewStates.LoadData(ViewModelResponse(postItSortedBy, isSuccessFullResponse))
        }.catch {
            ViewStates.Error("Something went wrong")
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewStates.Loading)


    fun handleIntent(intent: CRUDIntents) {
        when (intent) {
            is CRUDIntents.DeletePostIt -> {
                viewModelScope.launch {
                    when (useCases.deletePostItUseCase(intent.data)) {
                        Response.Error -> _isSuccessfulResponse.value = false
                        is Response.Success -> _isSuccessfulResponse.value = true
                    }
                }
            }

            is CRUDIntents.GetAllData -> {
                _sortedBy.value = intent.sortedVy
            }

            is CRUDIntents.UpsertPostIt -> {
                viewModelScope.launch {
                    when (useCases.addPostItUseCase(intent.data)) {
                        Response.Error -> _isSuccessfulResponse.value = false
                        is Response.Success -> _isSuccessfulResponse.value = true
                    }
                }
            }
        }
    }
}