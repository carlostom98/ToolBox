package com.poc.viewmodel.viewintents.createoreditintent

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CreateOrEditPostItViewModel: ViewModel() {
    private val _postItEntity = MutableStateFlow(PostItEntity())

    val postItEntity = _postItEntity

    fun handleIntent(createOrEditIntent: CreateOrEditIntent) {
        when (createOrEditIntent) {
            is CreateOrEditIntent.AddTitle -> {
                _postItEntity.update { it ->
                    it.copy(title = createOrEditIntent.title)
                }
            }

            is CreateOrEditIntent.AddDescription -> {
                _postItEntity.update { it ->
                    it.copy(description = createOrEditIntent.description)
                }
            }

            is CreateOrEditIntent.SetColor -> {
                _postItEntity.update { it ->
                    it.copy(color = createOrEditIntent.color)
                }
            }

            is CreateOrEditIntent.SetUrgencyLevel -> {
                _postItEntity.update { it ->
                    it.copy(urgencyLevel = createOrEditIntent.urgencyLevel)
                }
            }

            CreateOrEditIntent.resetPostItEntity ->  _postItEntity.update {
                it.copy(title = null, description = null, color = null, urgencyLevel = null)
            }
        }
    }

}