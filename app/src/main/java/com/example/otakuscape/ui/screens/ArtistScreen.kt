package com.example.otakuscape.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.presentation.MainViewModel
import com.example.otakuscape.ui.components.WallpaperCard
import com.example.otakuscape.ui.theme.PrimaryRed
import com.example.otakuscape.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistScreen(
    artistName: String,
    onBack: () -> Unit,
    onWallpaperClick: (Item) -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    val wallpapers by viewModel.wallpapers.collectAsState()
    val artistWallpapers = wallpapers.filter { it.artist_name == artistName }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Artist Profile")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Artist header
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Artist",
                    tint = PrimaryRed,
                    modifier = Modifier.size(64.dp)
                )
                
                Text(
                    text = artistName,
                    modifier = Modifier.padding(top = 8.dp),
                    color = TextWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "${artistWallpapers.size} wallpapers",
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                
                Button(
                    onClick = { /* TODO: Follow artist */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    ),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Follow Artist")
                }
            }
            
            // Artist's wallpapers
            if (artistWallpapers.isNotEmpty()) {
                Text(
                    text = "Wallpapers by $artistName",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = TextWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(artistWallpapers) { wallpaper ->
                        WallpaperCard(
                            wallpaper = wallpaper,
                            onClick = onWallpaperClick,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No wallpapers found for this artist",
                        color = TextWhite
                    )
                }
            }
        }
    }
}