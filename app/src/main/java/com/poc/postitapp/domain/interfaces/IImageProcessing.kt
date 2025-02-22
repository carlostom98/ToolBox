package com.poc.postitapp.domain.interfaces

import android.graphics.Bitmap

interface IImageProcessing {
    suspend fun getOriginalBitmap(url: String): Bitmap
    suspend fun applyFilter(source: Bitmap): Bitmap
}