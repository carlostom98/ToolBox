package com.example.cryptocurrency.domain.usecases

import android.graphics.Bitmap
import com.example.cryptocurrency.utils.BaseUseCase
import com.example.cryptocurrency.utils.ImageProcessing

class GetOriginalImage(private val imagesProcessor: ImageProcessing) :
    BaseUseCase<String, Bitmap>() {
    override suspend fun doWork(parameter: String): Bitmap {
        return imagesProcessor.getOriginalBitmap(parameter)
    }
}



