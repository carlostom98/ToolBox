package com.poc.viewmodel.viewintents

import androidx.lifecycle.ViewModel
import com.poc.domain.entities.SortedBy
import kotlinx.coroutines.flow.MutableStateFlow

class HandleSortedByStateViewModel: ViewModel() {
    private val _sortedBy = MutableStateFlow(SortedBy.TITLE)
    val sortedBy = _sortedBy

    fun setState(sortedBy: SortedBy) {
        _sortedBy.value = sortedBy
    }
}