package com.example.otakuscape.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.theme.ChipBackground
import com.example.otakuscape.ui.theme.TextWhite
import com.example.otakuscape.utils.ImageQualityChecker
import kotlinx.coroutines.launch

@Composable
fun ImageQualityDisplay(
    item: Item,
    modifier: Modifier = Modifier
) {
    var dimensions by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    LaunchedEffect(item.url) {
        launch {
            dimensions = ImageQualityChecker.getImageDimensions(item.url)
            isLoading = false
        }
    }
    
    val isHighRes = ImageQualityChecker.isHighResolution(dimensions)
    val orientation = ImageQualityChecker.getImageOrientation(dimensions)
    
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ChipBackground),
        border = BorderStroke(1.dp, if (isHighRes) Color.Green else Color.Yellow)
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = if (isLoading) {
                    "Loading..."
                } else {
                    val (width, height) = dimensions ?: Pair(0, 0)
                    "$width×$height • ${orientation.replaceFirstChar { it.uppercase() }}"
                },
                color = if (isHighRes) Color.Green else Color.Yellow,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}