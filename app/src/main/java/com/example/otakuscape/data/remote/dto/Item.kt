package com.example.otakuscape.data.remote.dto

data class Item(
    val artist_name: String?,
    val color_dominant: List<Int>?,
    val color_palette: List<List<Int>>?,
    val id: Int,
    val rating: String?,
    val source_url: String?,
    val tags: List<String>,
    val url: String
)