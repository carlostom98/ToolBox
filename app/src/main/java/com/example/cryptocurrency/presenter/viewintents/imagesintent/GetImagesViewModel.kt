package com.example.cryptocurrency.presenter.viewintents.imagesintent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.usecases.GetOriginalImage
import com.example.cryptocurrency.domain.usecases.GetProcessedImage
import com.example.cryptocurrency.domain.usecases.UseCases
import com.example.cryptocurrency.presenter.viewintents.ViewStates
import com.example.cryptocurrency.utils.ImageProcessing
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class GetImagesViewModel() : ViewModel() {
    private val getImages: UseCases = UseCases(
        getOriginalImage = GetOriginalImage(ImageProcessing),
        getProcessedImage = GetProcessedImage(ImageProcessing)
    )

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
                        getImages.getProcessedImage!!(viewModelScope, intent.bitmap) { leakedImage ->
                            _mainState.value = ViewStates.LoadData(leakedImage)
                        }
                    }

                    is ImageIntents.GetOriginalImage -> {
                        _mainState.value = ViewStates.Loading
                        getImages.getOriginalImage!!(viewModelScope, intent.url) { image ->
                            _mainState.value = ViewStates.LoadData(image)
                        }
                    }
                }
            }
        }
    }

}