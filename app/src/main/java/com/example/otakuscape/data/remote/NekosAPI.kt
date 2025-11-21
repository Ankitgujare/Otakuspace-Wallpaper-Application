package com.example.otakuscape.data.remote

import com.example.otakuscape.data.remote.dto.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface NekosAPI {
    @GET("v4/images/random")
    suspend fun getRandomImages(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("tags") tags: String? = null,
        @Query("without_tags") withoutTags: String? = null,
        @Query("rating") rating: String? = null,
        @Query("artist") artist: String? = null
    ): List<Item>
    
    @GET("v4/images/search")
    suspend fun searchImages(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0,
        @Query("tags") tags: String? = null,
        @Query("without_tags") withoutTags: String? = null,
        @Query("rating") rating: String? = null,
        @Query("artist") artist: String? = null
    ): List<Item>
}