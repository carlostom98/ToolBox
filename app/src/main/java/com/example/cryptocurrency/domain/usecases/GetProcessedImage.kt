package com.example.cryptocurrency.domain.usecases

import android.graphics.Bitmap
import com.example.cryptocurrency.utils.BaseUseCase
import com.example.cryptocurrency.utils.ImageProcessing
import javax.inject.Inject

class GetProcessedImage @Inject constructor (private val imagesProcessor: ImageProcessing) :
    BaseUseCase<Bitmap, Bitmap>() {
    override suspend fun doWork(parameter: Bitmap): Bitmap {
        return imagesProcessor.applyFilter(parameter)
    }
}