package com.example.otakuscape.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.components.ColorPaletteDisplay
import com.example.otakuscape.ui.components.ImageQualityDisplay
import com.example.otakuscape.ui.components.RatingDisplay
import com.example.otakuscape.ui.components.TagDisplay
import com.example.otakuscape.ui.theme.PrimaryRed
import com.example.otakuscape.ui.theme.TextWhite

@Composable
fun WallpaperCard(
    wallpaper: Item,
    onClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { 
                isSelected = !isSelected
                onClick(wallpaper)
            },
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, PrimaryRed) else null,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column {
            AsyncImage(
                model = wallpaper.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            
            // Artist information
            if (!wallpaper.artist_name.isNullOrEmpty()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp)
                        .clickable { 
                            wallpaper.artist_name?.let { onArtistClick(it) }
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Artist",
                        tint = TextWhite,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = wallpaper.artist_name ?: "",
                        modifier = Modifier.padding(start = 4.dp),
                        color = TextWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Text(
                text = wallpaper.tags.joinToString(", "),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                color = TextWhite,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            // Rating display
            RatingDisplay(
                item = wallpaper,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
            
            // Tag display
            TagDisplay(
                item = wallpaper,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            
            // Color palette display
            ColorPaletteDisplay(
                item = wallpaper,
                modifier = Modifier.padding(8.dp)
            )
            
            // Image quality display
            ImageQualityDisplay(
                item = wallpaper,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}