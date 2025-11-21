package com.example.otakuscape.data.remote

import com.example.otakuscape.data.remote.dto.AnimeResponse
import com.example.otakuscape.data.remote.dto.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApiService {
    @GET("images")
    suspend fun getAllImages(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("tags") tags: String? = null,
        @Query("without_tags") withoutTags: String? = null,
        @Query("rating") rating: String? = null,
        @Query("artist") artist: String? = null
    ): Response<AnimeResponse>

    @GET("images/random")
    suspend fun getRandomImages(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("tags") tags: String? = null,
        @Query("without_tags") withoutTags: String? = null,
        @Query("rating") rating: String? = null,
        @Query("artist") artist: String? = null
    ): Response<AnimeResponse>

    @GET("images/random/file")
    suspend fun getRandomImageFile(): Response<Unit>

    @GET("images/{id}")
    suspend fun getImageById(@Path("id") id: Int): Response<Item>

    @GET("images/{id}/file")
    suspend fun getImageFileById(@Path("id") id: Int): Response<Unit>
}

// Deprecated - replaced with AnimeResponse
data class ItemsResponse(
    val count: Int,
    val items: List<Item>
)