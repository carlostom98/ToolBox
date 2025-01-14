package com.carams.cryptocurrency.domain.usecases

import android.graphics.Bitmap
import com.carams.cryptocurrency.domain.interfaces.IImageProcessing
import com.carams.cryptocurrency.utils.BaseUseCase
import javax.inject.Inject

class GetProcessedImage @Inject constructor (private val imagesProcessor: IImageProcessing) :
    BaseUseCase<Bitmap, Bitmap>() {
    override suspend fun doWork(parameter: Bitmap): Bitmap {
        return imagesProcessor.applyFilter(parameter)
    }
}