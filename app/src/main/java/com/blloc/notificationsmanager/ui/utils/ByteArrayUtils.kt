package com.blloc.notificationsmanager.ui.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import java.io.ByteArrayOutputStream


class ByteArrayUtils {

    fun drawableToByteArray(drawable: Drawable): ByteArray {
        val bitmap = drawable.toBitmap()
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToDrawable(byteArray: ByteArray, resources: Resources): Drawable {
        return BitmapDrawable(
            resources,
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        )
    }

}