package com.example.cryptocurrency.presenter.viewintents.imagesintent

import android.graphics.Bitmap

sealed class ImageIntents {
    data class GetOriginalImage(val url: String): ImageIntents()
    data class GetLeakedImage(val bitmap: Bitmap): ImageIntents()
}