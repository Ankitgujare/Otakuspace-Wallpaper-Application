package com.example.otakuscape.utils

import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class ImageQualityChecker {
    companion object {
        private const val TAG = "ImageQualityChecker"
        
        /**
         * Get image dimensions without loading the full image
         * @param imageUrl URL of the image
         * @return Pair of width and height, or null if failed
         */
        suspend fun getImageDimensions(imageUrl: String): Pair<Int, Int>? {
            return withContext(Dispatchers.IO) {
                try {
                    val url = URL(imageUrl)
                    val options = BitmapFactory.Options().apply {
                        inJustDecodeBounds = true
                    }
                    BitmapFactory.decodeStream(url.openConnection().getInputStream(), null, options)
                    Pair(options.outWidth, options.outHeight)
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to get image dimensions", e)
                    null
                }
            }
        }
        
        /**
         * Check if image is landscape (width > height)
         * @param dimensions Pair of width and height
         * @return true if landscape, false otherwise
         */
        fun isLandscape(dimensions: Pair<Int, Int>?): Boolean {
            return dimensions?.let { (width, height) -> width > height } ?: false
        }
        
        /**
         * Check if image is portrait (height > width)
         * @param dimensions Pair of width and height
         * @return true if portrait, false otherwise
         */
        fun isPortrait(dimensions: Pair<Int, Int>?): Boolean {
            return dimensions?.let { (width, height) -> height > width } ?: false
        }
        
        /**
         * Check if image is square (width == height)
         * @param dimensions Pair of width and height
         * @return true if square, false otherwise
         */
        fun isSquare(dimensions: Pair<Int, Int>?): Boolean {
            return dimensions?.let { (width, height) -> width == height } ?: false
        }
        
        /**
         * Get image orientation
         * @param dimensions Pair of width and height
         * @return "landscape", "portrait", or "square"
         */
        fun getImageOrientation(dimensions: Pair<Int, Int>?): String {
            return when {
                isLandscape(dimensions) -> "landscape"
                isPortrait(dimensions) -> "portrait"
                isSquare(dimensions) -> "square"
                else -> "unknown"
            }
        }
        
        /**
         * Check if image resolution is high enough (720p or higher)
         * @param dimensions Pair of width and height
         * @return true if high resolution, false otherwise
         */
        fun isHighResolution(dimensions: Pair<Int, Int>?): Boolean {
            val minWidth = 1280 // 720p width
            val minHeight = 720  // 720p height
            return dimensions?.let { (width, height) -> 
                width >= minWidth && height >= minHeight
            } ?: false
        }
    }
}