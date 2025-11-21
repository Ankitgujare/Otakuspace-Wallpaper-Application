package com.example.otakuscape.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otakuscape.data.FavoritesManager
import com.example.otakuscape.data.RatingManager
import com.example.otakuscape.data.TagManager
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.usecase.DownloadImageUseCase
import com.example.otakuscape.domain.usecase.GetAllImagesUseCase
import com.example.otakuscape.domain.usecase.GetRandomImagesUseCase
import com.example.otakuscape.domain.usecase.ManageFavoritesUseCase
import com.example.otakuscape.domain.usecase.SetWallpaperUseCase
import com.example.otakuscape.utils.ColorUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllImagesUseCase: GetAllImagesUseCase,
    private val getRandomImagesUseCase: GetRandomImagesUseCase,
    private val manageFavoritesUseCase: ManageFavoritesUseCase,
    private val setWallpaperUseCase: SetWallpaperUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val favoritesManager: FavoritesManager
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _wallpapers = MutableStateFlow<List<Item>>(emptyList())
    val wallpapers: StateFlow<List<Item>> = _wallpapers

    init {
        loadWallpapers()
    }

    fun loadWallpapers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllImagesUseCase(limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    fun loadRandomWallpapers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getRandomImagesUseCase(limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    fun filterByTag(tag: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllImagesUseCase(tags = tag, limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }

    fun filterByRating(rating: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllImagesUseCase(rating = rating, limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
    
    fun toggleFavorite(item: Item) {
        viewModelScope.launch {
            manageFavoritesUseCase.toggleFavorite(item)
        }
    }
    
    fun isFavorite(item: Item): Boolean {
        return favoritesManager.isFavorite(item)
    }
    
    // New features
    
    fun loadSafeWallpapers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllImagesUseCase(rating = RatingManager.RATING_SAFE, limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
    
    fun loadRandomCatgirlWallpapers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getRandomImagesUseCase(tags = "catgirl", rating = RatingManager.RATING_SAFE, limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
    
    fun getTagRecommendations(item: Item): List<Item> {
        return TagManager.getTagRecommendations(item, _wallpapers.value)
    }
    
    fun filterByColor(colorName: String) {
        // This is a simplified implementation
        // In a real app, you would filter by actual color values
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            getAllImagesUseCase(tags = colorName, limit = 100).fold(
                onSuccess = { images ->
                    _wallpapers.value = images
                    _uiState.value = UiState.Success
                },
                onFailure = { error ->
                    _uiState.value = UiState.Error(error.message ?: "Unknown error")
                }
            )
        }
    }
    
    fun setAsWallpaper(item: Item) {
        viewModelScope.launch {
            setWallpaperUseCase(item.url).fold(
                onSuccess = { 
                    // Handle success
                },
                onFailure = { error ->
                    // Handle error
                }
            )
        }
    }
    
    fun downloadImage(item: Item) {
        viewModelScope.launch {
            val filename = "wallpaper_${item.id}"
            downloadImageUseCase(item.url, filename).fold(
                onSuccess = { 
                    // Handle success
                },
                onFailure = { error ->
                    // Handle error
                }
            )
        }
    }
    
    fun getFavorites(): List<Item> {
        return favoritesManager.getFavorites()
    }
}

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Error(val message: String) : UiState()
}