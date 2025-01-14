package com.carams.cryptocurrency.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import com.carams.cryptocurrency.domain.interfaces.IImageProcessing
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class ImageProcessing @Inject constructor(): IImageProcessing {

    override suspend fun getOriginalBitmap(url: String): Bitmap {
        return suspendCoroutine { continuation ->
            Picasso.get()
                .load(url)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        bitmap?.let(continuation::resume) ?: Log.e("IMAGE_LOADING", "Is null")
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        Log.e("IMAGE_LOADING", "Something went wrong")
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        Log.d("IMAGE_LOADING", "Is loading")
                    }
                })
        }
    }

    override suspend fun applyFilter(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height

        val pixels = IntArray(width * height)

        source.getPixels(pixels, 0, width, 0, 0, width, height)

        var R: Int
        var G: Int
        var B: Int
        var index: Int
        var treshold: Int

        for (y in 0 until height) {
            for (x in 0 until width) {
                index = y * width + x
                R = Color.red(pixels[index])
                G = Color.green(pixels[index])
                B = Color.blue(pixels[index])

                val grey = (R + G + B) / 3
                pixels[index] = Color.rgb(grey, grey, grey)
            }
        }

        val bitmapOut = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        bitmapOut.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmapOut
    }
}