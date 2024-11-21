package com.example.cryptocurrency.domain.usecases

import android.graphics.Bitmap
import com.example.cryptocurrency.domain.interfaces.IImageProcessing
import com.example.cryptocurrency.utils.BaseUseCase
import javax.inject.Inject

class GetOriginalImage @Inject constructor(private val imagesProcessor: IImageProcessing) :
    BaseUseCase<String, Bitmap>() {
    override suspend fun doWork(parameter: String): Bitmap {
        val bitmap: Bitmap = imagesProcessor.getOriginalBitmap(parameter)
        return bitmap
    }
}



