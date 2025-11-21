package com.example.otakuscape.data

import com.example.otakuscape.data.remote.dto.Item

object TagManager {
    fun getTagRecommendations(selectedItem: Item, allItems: List<Item>): List<Item> {
        val selectedTags = selectedItem.tags ?: emptyList()
        if (selectedTags.isEmpty()) return emptyList()
        
        return allItems
            .filter { item -> 
                item.id != selectedItem.id && // Exclude the selected item itself
                item.tags?.any { tag -> 
                    selectedTags.any { selectedTag -> 
                        tag.equals(selectedTag, ignoreCase = true) 
                    } 
                } ?: false
            }
            .take(10) // Limit to 10 recommendations
    }
}