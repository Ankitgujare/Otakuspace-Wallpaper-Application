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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.components.WallpaperCard

@Composable
fun WallpapersScreen(
    onWallpaperClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit = {}
) {
    // TODO: In a real app, this would come from the repository
    val wallpapers = listOf<Item>()
    
    if (wallpapers.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No wallpapers available",
                color = Color.White
            )
        }
    } else {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(wallpapers) { wallpaper ->
                WallpaperCard(
                    wallpaper = wallpaper,
                    onClick = onWallpaperClick,
                    onArtistClick = onArtistClick
                )
            }
        }
    }
}