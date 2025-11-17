package com.poc.viewmodel.viewintents.crudintent

import androidx.lifecycle.ViewModel
import com.poc.domain.usecases.GetAlbumsDetailedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ManageDataViewModel @Inject constructor(private val useCases: GetAlbumsDetailedUseCase) : ViewModel() {

//    private val _sortedBy = MutableStateFlow(SortedBy.DEFAULT)
//
//    private val _postItsSortedBy = _sortedBy.flatMapLatest { sortedBy ->
//        useCases.getAllPostItsUseCase(sortedBy).let {
//            if (it is Response.Success<Flow<List<PostItEntity>>>) it.response else emptyFlow()
//            } ?: emptyFlow()
//        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    private val _isSuccessfulResponse = MutableStateFlow(false)
//
//    val mainState = _postItsSortedBy.combine(_isSuccessfulResponse) { postItSortedBy, isSuccessFullResponse ->
//            ViewStates.LoadData(ViewModelResponse(postItSortedBy, isSuccessFullResponse))
//        }.catch {
//            ViewStates.Error("Something went wrong")
//        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ViewStates.Loading)
//




}