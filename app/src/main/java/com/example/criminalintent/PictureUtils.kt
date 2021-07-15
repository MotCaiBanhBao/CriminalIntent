package com.example.criminalintent

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Point
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

fun getScaleBitmap(path: String, destWidth: Int, destHeight: Int): Bitmap{
    //Read in the dimensions of the image on disk
    var options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(path, options)

    val srcWidth = options.outWidth.toFloat()
    val srcHeight = options.outHeight.toFloat()
    //Figure how must to scale down by
    var inSampleSize = 1
    if (srcHeight > destHeight || srcWidth > destWidth){
        var heightScale = srcHeight / destHeight
        var widthScale = srcWidth / destWidth

        val sampleScale = if (heightScale > widthScale){
            heightScale
        } else{
            widthScale
        }
        inSampleSize = sampleScale.roundToInt()
    }

    options = BitmapFactory.Options()
    options.inSampleSize = inSampleSize

    //Read in and create final bitmap
    return BitmapFactory.decodeFile(path, options)
}

fun getScaledBitmap(path: String, activity: Activity): Bitmap{
    val size = activity.resources.displayMetrics

    return getScaleBitmap(path, size.widthPixels, size.heightPixels)
}
