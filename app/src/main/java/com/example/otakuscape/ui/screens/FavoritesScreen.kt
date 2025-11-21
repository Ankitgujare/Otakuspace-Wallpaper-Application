package com.example.otakuscape.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.presentation.MainViewModel
import com.example.otakuscape.ui.components.WallpaperCard

@Composable
fun FavoritesScreen(
    onWallpaperClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit = {},
    viewModel: MainViewModel = hiltViewModel()
) {
    // Get favorites from the viewModel
    val favoriteWallpapers = remember { viewModel.getFavorites() }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (favoriteWallpapers.isEmpty()) {
            Text(
                text = "No favorites yet",
                color = Color.White
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(favoriteWallpapers) { wallpaper ->
                    WallpaperCard(
                        wallpaper = wallpaper,
                        onClick = onWallpaperClick,
                        onArtistClick = onArtistClick
                    )
                }
            }
        }
    }
}