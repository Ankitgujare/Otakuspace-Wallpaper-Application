package com.example.otakuscape.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.components.ColorPaletteDisplay
import com.example.otakuscape.ui.components.ImageQualityDisplay
import com.example.otakuscape.ui.components.RatingDisplay
import com.example.otakuscape.ui.theme.ChipBackground
import com.example.otakuscape.ui.theme.PrimaryRed
import com.example.otakuscape.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperDetailScreen(
    wallpaper: Item,
    onBack: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Wallpaper Details")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { isFavorite = !isFavorite }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) PrimaryRed else TextWhite
                        )
                    }
                    /*IconButton(onClick = { /* TODO: Handle download */ }) {
                        Icon(
                            imageVector = Icons.Default.Save,
                            contentDescription = "Download"
                        )
                    }*/
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Large header image
            AsyncImage(
                model = wallpaper.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            
            // Wallpaper title
            Text(
                text = wallpaper.tags.joinToString(", "),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                color = TextWhite,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            
            // Artist name
            wallpaper.artist_name?.let { artist ->
                Text(
                    text = "Artist: $artist",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = TextWhite,
                    fontSize = 16.sp
                )
            }
            
            // Rating
            wallpaper.rating?.let { rating ->
                Text(
                    text = "Rating: $rating",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = TextWhite,
                    fontSize = 16.sp
                )
            }
            
            // Resolution options
            Text(
                text = "Resolution Options",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                color = TextWhite,
                fontWeight = FontWeight.SemiBold
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { /* TODO: Handle resolution selection */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("1080p")
                }
                
                Button(
                    onClick = { /* TODO: Handle resolution selection */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("2K")
                }
                
                Button(
                    onClick = { /* TODO: Handle resolution selection */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("4K")
                }
                
                Button(
                    onClick = { /* TODO: Handle resolution selection */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("Mobile")
                }
            }
            
            // Tags
            Text(
                text = "Tags",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                color = TextWhite,
                fontWeight = FontWeight.SemiBold
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                wallpaper.tags.forEach { tag ->
                    androidx.compose.material3.Card(
                        modifier = Modifier.padding(4.dp),
                        colors = androidx.compose.material3.CardDefaults.cardColors(
                            containerColor = androidx.compose.ui.graphics.Color.DarkGray
                        )
                    ) {
                        Text(
                            text = tag,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            color = TextWhite
                        )
                    }
                }
            }
            
            // Color information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Color Information",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    color = TextWhite,
                    fontWeight = FontWeight.SemiBold
                )
                
                ColorPaletteDisplay(item = wallpaper)
            }
            
            // Rating information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Rating",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    color = TextWhite,
                    fontWeight = FontWeight.SemiBold
                )
                
                RatingDisplay(item = wallpaper)
            }
            
            // Image quality information
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Image Quality",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    color = TextWhite,
                    fontWeight = FontWeight.SemiBold
                )
                
                ImageQualityDisplay(item = wallpaper)
            }
            
            // User Ratings
            Text(
                text = "User Ratings",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                color = TextWhite,
                fontWeight = FontWeight.SemiBold
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        modifier = Modifier.size(24.dp),
                        tint = if (index < 4) Color.Yellow else Color.Gray
                    )
                }
                
                Text(
                    text = "4.0 (128 reviews)",
                    color = TextWhite,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            
            // Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { isFavorite = !isFavorite },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFavorite) PrimaryRed else androidx.compose.ui.graphics.Color.DarkGray
                    )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite"
                    )
                    Text(
                        text = if (isFavorite) "Added to Favorites" else "Add to Favorites",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                
                Button(
                    onClick = { /* TODO: Handle set as wallpaper */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("Set as Wallpaper")
                }
            }
        }
    }
}