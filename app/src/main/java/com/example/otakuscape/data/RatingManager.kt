package com.example.otakuscape.data

import com.example.otakuscape.data.remote.dto.Item

class RatingManager {
    companion object {
        // Rating levels
        const val RATING_SAFE = "safe"
        const val RATING_SUGGESTIVE = "suggestive"
        const val RATING_BORDERLINE = "borderline"
        const val RATING_EXPLICIT = "explicit"
        
        // Rating hierarchy (from safest to most explicit)
        private val RATING_HIERARCHY = listOf(
            RATING_SAFE,
            RATING_SUGGESTIVE,
            RATING_BORDERLINE,
            RATING_EXPLICIT
        )
        
        /**
         * Check if a rating is safe
         * @param rating The rating to check
         * @return true if safe, false otherwise
         */
        fun isSafeRating(rating: String?): Boolean {
            return rating?.equals(RATING_SAFE, ignoreCase = true) ?: true
        }
        
        /**
         * Filter items by maximum rating
         * @param items List of items to filter
         * @param maxRating Maximum allowed rating
         * @return Filtered list of items
         */
        fun filterByMaxRating(items: List<Item>, maxRating: String): List<Item> {
            val maxRatingIndex = RATING_HIERARCHY.indexOf(maxRating.lowercase())
            if (maxRatingIndex == -1) return items
            
            return items.filter { item ->
                val itemRatingIndex = item.rating?.let { RATING_HIERARCHY.indexOf(it.lowercase()) } ?: 0
                itemRatingIndex <= maxRatingIndex
            }
        }
        
        /**
         * Get all items with safe rating
         * @param items List of items to filter
         * @return List of safe items
         */
        fun getSafeItems(items: List<Item>): List<Item> {
            return items.filter { item ->
                isSafeRating(item.rating)
            }
        }
        
        /**
         * Check if an item requires a warning
         * @param item The item to check
         * @return true if item requires warning, false otherwise
         */
        fun requiresWarning(item: Item): Boolean {
            return when (item.rating?.lowercase()) {
                RATING_SUGGESTIVE, RATING_BORDERLINE, RATING_EXPLICIT -> true
                else -> false
            }
        }
    }
}