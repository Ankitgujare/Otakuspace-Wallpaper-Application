package com.example.otakuscape.data.remote.dto

data class imagesItem(
    val artist: Any,
    val color: Color,
    val id: Int,
    val rating: String,
    val source: Source,
    val tags: List<String>,
    val url: String
)