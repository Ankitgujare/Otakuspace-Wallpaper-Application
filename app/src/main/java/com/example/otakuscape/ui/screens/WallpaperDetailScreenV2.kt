package com.example.otakuscape.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.components.ColorPaletteDisplay
import com.example.otakuscape.ui.components.TagDisplay
import com.example.otakuscape.ui.theme.*

@Composable
fun WallpaperDetailScreenV2(
    wallpaper: Item,
    onBack: () -> Unit,
    onDownload: () -> Unit,
    onFavoriteToggle: () -> Unit,
    isFavorite: Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") }, // Empty title as requested
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onFavoriteToggle) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) PrimaryRed else TextWhite
                        )
                    }
                    
                    IconButton(onClick = onDownload) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Download"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(BackgroundDark)
        ) {
            // Wallpaper image
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                ) {
                    AsyncImage(
                        model = wallpaper.url,
                        contentDescription = wallpaper.artist_name ?: "Wallpaper",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Quality badge (simplified)
                    Card(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(containerColor = PrimaryRed)
                    ) {
                        Text(
                            text = wallpaper.rating ?: "HD",
                            color = Color.White,
                            modifier = Modifier.padding(8.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }
            
            // Wallpaper info
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Title and rating
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = wallpaper.artist_name ?: "Unknown Artist",
                            color = TextWhite,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        
                        // Rating
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = PrimaryRed,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = wallpaper.rating ?: "N/A",
                                color = TextWhite,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    // Artist info
                    if (wallpaper.artist_name != null) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { /* Navigate to artist page */ }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            // Artist avatar
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryRed)
                            ) {
                                Text(
                                    text = wallpaper.artist_name.first().toString(),
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            
                            Spacer(modifier = Modifier.width(8.dp))
                            
                            Text(
                                text = wallpaper.artist_name,
                                color = TextWhite,
                                fontSize = 16.sp
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Tags
                    Text(
                        text = "Tags",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    TagDisplay(
                        item = wallpaper,
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Color palette
                    Text(
                        text = "Color Palette",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    ColorPaletteDisplay(item = wallpaper)
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Source info (simplified)
                    Text(
                        text = "Source Info",
                        color = TextWhite,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = CardBackground)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Source: ${wallpaper.source_url ?: "Unknown"}",
                                color = TextWhite
                            )
                            Text(
                                text = "Rating: ${wallpaper.rating ?: "Unknown"}",
                                color = TextGray
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Actions
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                // Set as wallpaper
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryRed
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Set Wallpaper")
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Button(
                            onClick = onDownload,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ChipBackground
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Download",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Download")
                            }
                        }
                    }
                }
            }
        }
    }
}