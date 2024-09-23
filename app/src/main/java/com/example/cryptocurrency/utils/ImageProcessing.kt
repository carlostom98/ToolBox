package com.example.cryptocurrency.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import java.net.URL

object ImageProcessing {

    fun getOriginalBitmap(url: String): Bitmap {
        return URL(url).openStream().use {
            BitmapFactory.decodeStream(it)
        }
    }

    fun applyFilter(source: Bitmap): Bitmap {
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