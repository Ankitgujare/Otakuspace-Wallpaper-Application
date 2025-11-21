package com.example.otakuscape.data.remote.dto

data class AnimeResponse(
    val count: Int,
    val items: List<Item>,
    val limit: Int?,
    val offset: Int?,
    val total: Int?
)