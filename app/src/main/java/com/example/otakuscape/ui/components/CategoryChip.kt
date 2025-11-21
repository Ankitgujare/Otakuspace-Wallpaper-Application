package com.example.otakuscape.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.otakuscape.ui.theme.ChipBackground
import com.example.otakuscape.ui.theme.PrimaryRed
import com.example.otakuscape.ui.theme.TextWhite

@Composable
fun CategoryChip(
    category: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isSelected by remember { mutableStateOf(false) }
    
    Card(
        modifier = modifier
            .clickable { 
                isSelected = !isSelected
                onClick(category)
            },
        shape = RoundedCornerShape(16.dp),
        border = if (isSelected) BorderStroke(2.dp, PrimaryRed) else BorderStroke(1.dp, ChipBackground),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) PrimaryRed else ChipBackground
        )
    ) {
        Text(
            text = category,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = if (isSelected) Color.White else TextWhite
        )
    }
}