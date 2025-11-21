package com.example.otakuscape.data.repository

import android.content.Context
import com.example.otakuscape.utils.WallpaperSetter
import javax.inject.Inject

class WallpaperRepository @Inject constructor(
    private val context: Context
) {
    private val wallpaperSetter = WallpaperSetter(context)
    
    suspend fun setAsWallpaper(url: String): Result<Unit> {
        return wallpaperSetter.setAsWallpaper(url)
    }
}