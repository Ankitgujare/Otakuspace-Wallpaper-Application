package com.example.otakuscape.utils

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class WallpaperSetter(private val context: Context) {
    
    suspend fun setAsWallpaper(url: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                // Load the image using Coil
                val loader = ImageLoader(context)
                val request = ImageRequest.Builder(context)
                    .data(url)
                    .build()
                
                val result = (loader.execute(request) as SuccessResult).drawable
                
                // Convert drawable to bitmap
                val bitmap = result.toBitmap()
                
                // Set as wallpaper
                setWallpaper(bitmap)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    private fun setWallpaper(bitmap: Bitmap) {
        val wallpaperManager = WallpaperManager.getInstance(context)
        
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // For Android N and above, we can set both home and lock screen wallpaper
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            } else {
                // For older versions
                wallpaperManager.setBitmap(bitmap)
            }
        } catch (e: IOException) {
            throw e
        }
    }
}