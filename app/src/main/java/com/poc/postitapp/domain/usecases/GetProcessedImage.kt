package com.poc.postitapp.domain.usecases

import android.graphics.Bitmap
import com.poc.postitapp.domain.interfaces.IImageProcessing
import com.poc.postitapp.utils.BaseUseCase
import javax.inject.Inject

class GetProcessedImage @Inject constructor (private val imagesProcessor: IImageProcessing) :
    BaseUseCase<Bitmap, Bitmap>() {
    override suspend fun doWork(parameter: Bitmap): Bitmap {
        return imagesProcessor.applyFilter(parameter)
    }
}