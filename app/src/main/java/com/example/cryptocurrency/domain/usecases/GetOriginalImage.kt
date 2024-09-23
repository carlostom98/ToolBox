package com.example.cryptocurrency.domain.usecases

import android.graphics.Bitmap
import com.example.cryptocurrency.utils.BaseUseCase
import com.example.cryptocurrency.utils.ImageProcessing

class GetOriginalImage(private val imagesProcessor: ImageProcessing) :
    BaseUseCase<String, Bitmap>() {
    override suspend fun doWork(parameter: String): Bitmap {
        var bitmap: Bitmap = imagesProcessor.getOriginalBitmap(parameter)
        return bitmap
    }
}



