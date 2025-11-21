package com.example.otakuscape.data.repository

import com.example.otakuscape.data.remote.AnimeApiService
import com.example.otakuscape.data.remote.dto.AnimeResponse
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.repository.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val api: AnimeApiService
) : AnimeRepository {
    
    override suspend fun getAllImages(
        limit: Int?,
        offset: Int?,
        tags: String?,
        withoutTags: String?,
        rating: String?,
        artist: String?
    ) = try {
        val response = api.getAllImages(limit, offset, tags, withoutTags, rating, artist)
        if (response.isSuccessful) {
            Result.success(response.body()?.items ?: emptyList())
        } else {
            Result.failure(Exception("Failed to fetch images: ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getRandomImages(
        limit: Int?,
        offset: Int?,
        tags: String?,
        withoutTags: String?,
        rating: String?,
        artist: String?
    ) = try {
        val response = api.getRandomImages(limit, offset, tags, withoutTags, rating, artist)
        if (response.isSuccessful) {
            Result.success(response.body()?.items ?: emptyList())
        } else {
            Result.failure(Exception("Failed to fetch random images: ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getRandomImageFile() = try {
        val response = api.getRandomImageFile()
        if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Failed to fetch random image file: ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getImageById(id: Int) = try {
        val response = api.getImageById(id)
        if (response.isSuccessful) {
            response.body()?.let { Result.success(it) }
                ?: Result.failure(Exception("Image not found"))
        } else {
            Result.failure(Exception("Failed to fetch image: ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getImageFileById(id: Int) = try {
        val response = api.getImageFileById(id)
        if (response.isSuccessful) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Failed to fetch image file: ${response.message()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}