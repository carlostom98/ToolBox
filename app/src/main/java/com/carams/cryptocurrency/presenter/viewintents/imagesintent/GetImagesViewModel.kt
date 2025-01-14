package com.carams.cryptocurrency.presenter.viewintents.imagesintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carams.cryptocurrency.domain.usecases.UseCases
import com.carams.cryptocurrency.presenter.viewintents.ViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetImagesViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    val userIntent = Channel<ImageIntents>(Channel.UNLIMITED)
    private val _mainState = MutableStateFlow<ViewStates>(ViewStates.Idle)
    val mainState get() = _mainState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect() { intent ->
                when (intent) {
                    is ImageIntents.GetLeakedImage -> {
                        _mainState.value = ViewStates.Loading
                        useCases.getProcessedImage!!(viewModelScope, intent.bitmap) { leakedImage ->
                            _mainState.value = ViewStates.LoadData(leakedImage)
                        }
                    }

                    is ImageIntents.GetOriginalImage -> {
                        _mainState.value = ViewStates.Loading
                        useCases.getOriginalImage!!(viewModelScope, intent.url) { image ->
                            _mainState.value = ViewStates.LoadData(image)
                        }
                    }
                }
            }
        }
    }

}