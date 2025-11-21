package com.example.otakuscape.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.presentation.MainViewModel
import com.example.otakuscape.ui.components.WallpaperCard

@Composable
fun RecommendationsScreen(
    viewModel: MainViewModel = hiltViewModel(),
    selectedItem: Item? = null,
    onWallpaperClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit = {}
) {
    val recommendations = if (selectedItem != null) {
        viewModel.getTagRecommendations(selectedItem)
    } else {
        // Show general recommendations
        emptyList()
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectedItem != null) {
            Text(
                text = "Based on your selection:",
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            // Show the selected item
            selectedItem.let { wallpaper ->
                WallpaperCard(
                    wallpaper = wallpaper,
                    onClick = onWallpaperClick,
                    onArtistClick = onArtistClick,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            
            Text(
                text = "Recommended for you:",
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            Text(
                text = "Select a wallpaper to see personalized recommendations",
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        if (recommendations.isNotEmpty()) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(recommendations) { wallpaper ->
                    WallpaperCard(
                        wallpaper = wallpaper,
                        onClick = onWallpaperClick,
                        onArtistClick = onArtistClick
                    )
                }
            }
        } else {
            Text(
                text = "No recommendations yet",
                color = Color.White
            )
        }
    }
}