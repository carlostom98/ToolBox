package com.example.cryptocurrency.presenter.viewintents.imagesintent

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.usecases.GetOriginalImage
import com.example.cryptocurrency.domain.usecases.GetProcessedImage
import com.example.cryptocurrency.domain.usecases.UseCases
import com.example.cryptocurrency.presenter.viewintents.mainintents.MainIntent
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
                when(intent) {
                    is ImageIntents.GetLeakedImage -> getImages.getProcessedImage(viewModelScope, intent.bitmap) { leakedImage ->
                        _mainState.value = ViewStates.
                    }
                    is ImageIntents.GetOriginalImage -> getImages.getOriginalImage(viewModelScope, intent.url) { image ->

                    }
                }
            }
        }
    }







}