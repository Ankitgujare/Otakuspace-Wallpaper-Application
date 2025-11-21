package com.example.otakuscape.domain.usecase

import android.content.Context
import com.example.otakuscape.utils.ImageDownloader
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val context: Context
) {
    private val imageDownloader = ImageDownloader(context)
    
    suspend operator fun invoke(url: String, filename: String): Result<Unit> {
        return imageDownloader.downloadImage(url, filename).map { }
    }
}