package com.example.otakuscape.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.otakuscape.ui.theme.TextWhite

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // User avatar
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Avatar",
            modifier = Modifier.size(100.dp),
            tint = TextWhite
        )
        
        // Username
        Text(
            text = "Anime Lover",
            color = TextWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        
        // Stats section
        Text(
            text = "Your Stats",
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
        )
        
        // Stats cards
        StatsCard(
            title = "Total Downloads",
            value = "0",
            modifier = Modifier.fillMaxWidth()
        )
        
        StatsCard(
            title = "Favorite Wallpapers",
            value = "0",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        
        StatsCard(
            title = "Recent Activity",
            value = "No activity yet",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        
        // Settings section
        Text(
            text = "Settings",
            color = TextWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, bottom = 16.dp)
        )
        
        // TODO: Add settings options
        
        // Logout button
        // TODO: Add logout button
    }
}

@Composable
fun StatsCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = TextWhite,
            fontSize = 16.sp
        )
        
        Text(
            text = value,
            color = Color.LightGray,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}