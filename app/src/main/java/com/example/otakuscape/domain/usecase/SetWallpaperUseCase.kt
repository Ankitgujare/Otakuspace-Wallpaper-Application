package com.example.otakuscape.domain.usecase

import com.example.otakuscape.data.repository.WallpaperRepository
import javax.inject.Inject

class SetWallpaperUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(url: String): Result<Unit> {
        return wallpaperRepository.setAsWallpaper(url)
    }
}