package com.example.otakuscape.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImageDownloader(private val context: Context) {
    
    suspend fun downloadImage(url: String, filename: String): Result<Uri> {
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
                
                // Save the bitmap
                val uri = saveImageToGallery(bitmap, filename)
                Result.success(uri)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    private fun saveImageToGallery(bitmap: Bitmap, filename: String): Uri {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/OtakuScape")
            }
        }
        
        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        
        uri?.let { imageUri ->
            try {
                resolver.openOutputStream(imageUri).use { outputStream ->
                    if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)) {
                        throw IOException("Failed to save bitmap")
                    }
                }
                return imageUri
            } catch (e: Exception) {
                resolver.delete(imageUri, null, null)
                throw e
            }
        } ?: throw IOException("Failed to create new MediaStore record")
    }
    
    // For older Android versions
    private fun saveImageToFile(bitmap: Bitmap, filename: String): Uri {
        val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val imageFile = File(imagesDir, "OtakuScape/$filename.jpg")
        
        // Create directory if it doesn't exist
        imageFile.parentFile?.mkdirs()
        
        FileOutputStream(imageFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }
        
        return Uri.fromFile(imageFile)
    }
}