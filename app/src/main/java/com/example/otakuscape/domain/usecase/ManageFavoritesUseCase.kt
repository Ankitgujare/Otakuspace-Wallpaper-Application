package com.example.otakuscape.domain.usecase

import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    fun getFavorites(): Flow<List<Item>> {
        return favoritesRepository.getFavorites()
    }
    
    suspend fun addFavorite(item: Item) {
        favoritesRepository.addFavorite(item)
    }
    
    suspend fun removeFavorite(item: Item) {
        favoritesRepository.removeFavorite(item)
    }
    
    suspend fun isFavorite(item: Item): Boolean {
        return favoritesRepository.isFavorite(item)
    }
    
    suspend fun toggleFavorite(item: Item) {
        if (isFavorite(item)) {
            removeFavorite(item)
        } else {
            addFavorite(item)
        }
    }
}