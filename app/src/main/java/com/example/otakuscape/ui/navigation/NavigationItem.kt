package com.example.otakuscape.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp

import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavigationItem(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    
    object Favorites : NavigationItem(
        route = "favorites",
        title = "Favs",
        icon = Icons.Default.Favorite
    )
    
    object Profile : NavigationItem(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    
    object Recommendations : NavigationItem(
        route = "recommendations",
        title = "Recs",
        icon = Icons.Default.ThumbUp
    )
}