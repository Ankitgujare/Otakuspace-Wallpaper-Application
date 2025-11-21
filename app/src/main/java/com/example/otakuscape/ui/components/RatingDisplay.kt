package com.example.otakuscape.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuscape.data.RatingManager
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.ui.theme.ChipBackground
import com.example.otakuscape.ui.theme.TextWhite

@Composable
fun RatingDisplay(
    item: Item,
    modifier: Modifier = Modifier
) {
    val rating = item.rating ?: RatingManager.RATING_SAFE
    val ratingColor = when (rating.lowercase()) {
        RatingManager.RATING_SAFE -> Color.Green
        RatingManager.RATING_SUGGESTIVE -> Color.Yellow
        RatingManager.RATING_BORDERLINE -> Color(0xFFFFA500) // Orange
        RatingManager.RATING_EXPLICIT -> Color.Red
        else -> Color.Gray
    }
    
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ChipBackground),
        border = BorderStroke(1.dp, ratingColor)
    ) {
        Row(
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
            Text(
                text = rating.replaceFirstChar { it.uppercase() },
                color = ratingColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}