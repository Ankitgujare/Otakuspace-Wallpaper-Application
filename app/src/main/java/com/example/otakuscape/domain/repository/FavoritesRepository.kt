package com.example.otakuscape.domain.repository

import com.example.otakuscape.data.remote.dto.Item
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun getFavorites(): Flow<List<Item>>
    suspend fun addFavorite(item: Item)
    suspend fun removeFavorite(item: Item)
    suspend fun isFavorite(item: Item): Boolean
}