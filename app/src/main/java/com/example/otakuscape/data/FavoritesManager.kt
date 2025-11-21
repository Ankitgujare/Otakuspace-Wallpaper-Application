package com.example.otakuscape.data

import android.content.Context
import com.example.otakuscape.data.remote.dto.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
    private val fileName = "favorites.json"
    
    fun getFavorites(): List<Item> {
        return try {
            val fileContents = context.openFileInput(fileName).bufferedReader().use { it.readText() }
            if (fileContents.isNotEmpty()) {
                val itemType = object : TypeToken<List<Item>>() {}.type
                gson.fromJson(fileContents, itemType)
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            // If file doesn't exist or is corrupted, return empty list
            emptyList()
        }
    }
    
    fun addFavorite(item: Item) {
        val favorites = getFavorites().toMutableList()
        
        // Check if item already exists
        if (favorites.none { it.id == item.id }) {
            favorites.add(item)
            saveFavorites(favorites)
        }
    }
    
    fun removeFavorite(item: Item) {
        val favorites = getFavorites().toMutableList()
        favorites.removeAll { it.id == item.id }
        saveFavorites(favorites)
    }
    
    fun isFavorite(item: Item): Boolean {
        return getFavorites().any { it.id == item.id }
    }
    
    private fun saveFavorites(favorites: List<Item>) {
        try {
            val jsonString = gson.toJson(favorites)
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(jsonString.toByteArray())
            }
        } catch (e: Exception) {
            // Handle file write error
        }
    }
}