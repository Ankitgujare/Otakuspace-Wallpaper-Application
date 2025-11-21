package com.example.otakuscape.domain.repository

import com.example.otakuscape.data.remote.dto.Item
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    suspend fun getAllImages(
        limit: Int? = null,
        offset: Int? = null,
        tags: String? = null,
        withoutTags: String? = null,
        rating: String? = null,
        artist: String? = null
    ): Result<List<Item>>

    suspend fun getRandomImages(
        limit: Int? = null,
        offset: Int? = null,
        tags: String? = null,
        withoutTags: String? = null,
        rating: String? = null,
        artist: String? = null
    ): Result<List<Item>>

    suspend fun getRandomImageFile(): Result<Unit>

    suspend fun getImageById(id: Int): Result<Item>

    suspend fun getImageFileById(id: Int): Result<Unit>
}