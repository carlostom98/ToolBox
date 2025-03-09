package com.poc.postitapp.presenter.viewintents.crudintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.postitapp.domain.entities.SortedBy
import com.poc.postitapp.domain.entities.UseCaseResponse
import com.poc.postitapp.domain.usecases.UseCases
import com.poc.postitapp.presenter.viewintents.ViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ManageDataViewModel @Inject constructor(private val useCases: UseCases): ViewModel() {

    private val _sortedBy = MutableStateFlow(SortedBy.DEFAULT)

    private val _contacts = _sortedBy.flatMapLatest { sortedBy ->
        useCases.getAllPostItsUseCase(sortedBy)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _mainState = MutableStateFlow<ViewStates>(ViewStates.Loading)
    val mainState = _contacts.map { contacts ->
        ViewStates.LoadData(contacts)
    }.catch {
        ViewStates.Error(it.message)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewStates.Loading)

    val mainStateShared = _mainState.shareIn(viewModelScope, SharingStarted.Lazily, replay = 1)


    fun handleIntent(intent: CRUDIntents) {
        _mainState.value = ViewStates.Loading
        when(intent) {
            is CRUDIntents.DeletePostIt -> {
                viewModelScope.launch {
                    useCases.deletePostItUseCase(this, intent.data) { useCaseResponse ->
                        handleMainStateResult(useCaseResponse)
                    }
                }
            }
            CRUDIntents.GetAllData -> {
                _sortedBy.value = SortedBy.DEFAULT
            }
            CRUDIntents.GetAllDataSorted -> {
                _sortedBy.value = SortedBy.TITLE
            }
            is CRUDIntents.UpsertPostIt -> {
                viewModelScope.launch {
                    useCases.addPostItUseCase(this, intent.data) { useCaseResponse ->
                        handleMainStateResult(useCaseResponse)
                    }
                }
            }
        }
    }

    private fun handleMainStateResult(useCaseResponse: UseCaseResponse) {
        when(useCaseResponse){
            UseCaseResponse.Error -> _mainState.value = ViewStates.Error("Error Modifying Data")
            UseCaseResponse.Success -> _mainState.value = ViewStates.LoadData(null)
        }
    }
}