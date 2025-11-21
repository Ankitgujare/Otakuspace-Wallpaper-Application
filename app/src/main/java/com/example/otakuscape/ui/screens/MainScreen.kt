package com.example.otakuscape.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.presentation.MainViewModel
import com.example.otakuscape.ui.components.BottomNavigationBar
import com.example.otakuscape.ui.navigation.NavigationItem

// Data classes for your API response
data class Anime(
    val id: Int,
    val title: String,
    val author: String? = null,
    val imageUrl: String,
    val categories: List<String> = emptyList(),
    val isFeatured: Boolean = false
)

data class Category(
    val id: Int,
    val name: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    var selectedWallpaper by remember { mutableStateOf<Item?>(null) }
    var selectedArtist by remember { mutableStateOf<String?>(null) }
    
    Scaffold(
        // Removed the topBar
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            
            // Show bottom navigation bar only on main screens, not on detail screens
            if (currentRoute == NavigationItem.Home.route || 
                currentRoute == NavigationItem.Favorites.route || 
                currentRoute == NavigationItem.Profile.route) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(
                navController = navController,
                viewModel = viewModel,
                onWallpaperClick = { wallpaper ->
                    selectedWallpaper = wallpaper
                    navController.navigate("wallpaper_detail/${wallpaper.id}")
                },
                onArtistClick = { artistName ->
                    selectedArtist = artistName
                    navController.navigate("artist/$artistName")
                },
                selectedWallpaper = selectedWallpaper,
                selectedArtist = selectedArtist,
                onBackFromDetail = { navController.popBackStack() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    onWallpaperClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit,
    selectedWallpaper: Item?,
    selectedArtist: String?,
    onBackFromDetail: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Home.route
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen(
                viewModel = viewModel, 
                onWallpaperClick = onWallpaperClick, 
                onArtistClick = onArtistClick
            )
        }
        
        composable(NavigationItem.Favorites.route) {
            FavoritesScreen(
                onWallpaperClick = onWallpaperClick,
                onArtistClick = onArtistClick,
                viewModel = viewModel
            )
        }
        
        composable("recommendations") {
            RecommendationsScreen(
                viewModel = viewModel,
                selectedItem = selectedWallpaper,
                onWallpaperClick = onWallpaperClick,
                onArtistClick = onArtistClick
            )
        }
        
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
        
        composable("wallpaper_detail/{wallpaperId}") { backStackEntry ->
            val wallpaperId = backStackEntry.arguments?.getString("wallpaperId")?.toInt() ?: 0
            selectedWallpaper?.let { wallpaper ->
                WallpaperDetailScreenV2(
                    wallpaper = wallpaper,
                    onBack = onBackFromDetail,
                    onDownload = { viewModel.downloadImage(wallpaper) },
                    onFavoriteToggle = { viewModel.toggleFavorite(wallpaper) },
                    isFavorite = viewModel.isFavorite(wallpaper)
                )
            }
        }
        
        composable("artist/{artistName}") { backStackEntry ->
            val artistName = backStackEntry.arguments?.getString("artistName") ?: ""
            selectedArtist?.let { artist ->
                ArtistScreen(
                    artistName = artist,
                    onBack = onBackFromDetail,
                    onWallpaperClick = onWallpaperClick,
                    viewModel = viewModel
                )
            }
        }
    }
}

// Main composable that will use your API data
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeHomeScreen(
    watchlistAnimes: List<Anime> = emptyList(),
    featuredAnimes: List<Anime> = emptyList(),
    categories: List<Category> = emptyList(),
    onSearch: (String) -> Unit = {},
    onAnimeClick: (Anime) -> Unit = {},
    onCategoryClick: (Category) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F23))
    ) {
        // Watchlist Section
        item {
            Text(
                text = "Watchlist",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        items(watchlistAnimes) { anime ->
            WatchlistItem(anime = anime, onAnimeClick = onAnimeClick)
        }
        
        // Search Section
        item {
            Text(
                text = "Search for animes",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it },
                onSearch = { onSearch(searchQuery) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        
        // Categories Section
        item {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
            
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories) { category ->
                    CategoryChip(category = category, onCategoryClick = onCategoryClick)
                }
            }
        }
        
        // Featured Anime Section
        item {
            Text(
                text = "Featured Anime",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        items(featuredAnimes) { anime ->
            FeaturedAnimeItem(anime = anime, onAnimeClick = onAnimeClick)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistItem(
    anime: Anime,
    onAnimeClick: (Anime) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onAnimeClick(anime) },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "by ${anime.author ?: "Unknown"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF888899)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        placeholder = {
            Text(
                text = "Search for anime...",
                color = Color(0xFF888899)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF888899)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp)),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            containerColor = Color(0xFF1A1A2E)
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryChip(
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable { onCategoryClick(category) },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2D2D44))
    ) {
        Text(
            text = category.name,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 14.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeaturedAnimeItem(
    anime: Anime,
    onAnimeClick: (Anime) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onAnimeClick(anime) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            
            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0xCC000000)
                            )
                        )
                    )
            )
            
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = anime.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Button(
                    onClick = { onAnimeClick(anime) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text(text = "Watch Now")
                }
            }
        }
    }
}