package com.example.otakuscape.data.repository

import com.example.otakuscape.data.FavoritesManager
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesManager: FavoritesManager
) : FavoritesRepository {
    
    override fun getFavorites(): Flow<List<Item>> = flow {
        emit(favoritesManager.getFavorites())
    }
    
    override suspend fun addFavorite(item: Item) {
        favoritesManager.addFavorite(item)
    }
    
    override suspend fun removeFavorite(item: Item) {
        favoritesManager.removeFavorite(item)
    }
    
    override suspend fun isFavorite(item: Item): Boolean {
        return favoritesManager.isFavorite(item)
    }
}