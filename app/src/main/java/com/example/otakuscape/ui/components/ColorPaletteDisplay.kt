package com.example.otakuscape.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.utils.ColorUtils

@Composable
fun ColorPaletteDisplay(item: Item, modifier: Modifier = Modifier) {
    val paletteColors = ColorUtils.getPaletteColors(item)
    val dominantColor = ColorUtils.getDominantColor(item)
    
    if (paletteColors.isEmpty()) return
    
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dominant color indicator
        dominantColor?.let { color ->
            Card(
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = color),
                border = BorderStroke(1.dp, Color.White)
            ) {}
            
            Text(
                text = "Dominant",
                fontSize = 12.sp,
                color = Color.White
            )
        }
        
        // Palette colors
        Text(
            text = "Palette:",
            fontSize = 12.sp,
            color = Color.White
        )
        
        paletteColors.take(5).forEach { color ->
            Card(
                modifier = Modifier.size(16.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = color),
                border = BorderStroke(1.dp, Color.White)
            ) {}
        }
    }
}