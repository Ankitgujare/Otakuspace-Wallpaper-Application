package com.example.otakuscape.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.presentation.MainViewModel
import com.example.otakuscape.presentation.UiState
import com.example.otakuscape.ui.components.CategoryChip
import com.example.otakuscape.ui.components.WallpaperCard
import com.example.otakuscape.ui.theme.ChipBackground
import com.example.otakuscape.ui.theme.PrimaryRed
import com.example.otakuscape.ui.theme.TextGray
import com.example.otakuscape.ui.theme.TextWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel = hiltViewModel(),
    onWallpaperClick: (Item) -> Unit,
    onArtistClick: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val wallpapers by viewModel.wallpapers.collectAsState()
    
    var searchQuery by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("OtakuScape")
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search bar
            TextField(
                value = searchQuery,
                onValueChange = { 
                    searchQuery = it
                    // Perform search as user types
                    if (it.isNotEmpty()) {
                        viewModel.filterByTag(it)
                    } else {
                        viewModel.loadWallpapers()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = {
                    Text(
                        text = "Search for wallpapers...",
                        color = TextGray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = TextGray
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = TextWhite,
                    containerColor = ChipBackground
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            )
            
            // Safe mode toggle
            var isSafeMode by remember { mutableStateOf(true) }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Safe Mode",
                    color = TextWhite,
                    fontWeight = FontWeight.Medium
                )
                
                androidx.compose.material3.Switch(
                    checked = isSafeMode,
                    onCheckedChange = { checked ->
                        isSafeMode = checked
                        if (checked) {
                            viewModel.loadSafeWallpapers()
                        } else {
                            viewModel.loadWallpapers()
                        }
                    },
                    colors = androidx.compose.material3.SwitchDefaults.colors(
                        checkedThumbColor = PrimaryRed,
                        checkedTrackColor = PrimaryRed.copy(alpha = 0.5f)
                    )
                )
            }
            
            // Quick actions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.loadRandomCatgirlWallpapers() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ChipBackground
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Catgirls", fontSize = 12.sp)
                }
                
                Button(
                    onClick = { viewModel.filterByColor("blue") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ChipBackground
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Blue", fontSize = 12.sp)
                }
                
                Button(
                    onClick = { viewModel.filterByColor("red") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ChipBackground
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Red", fontSize = 12.sp)
                }
            }
            
            // Section title
            Text(
                text = "Featured Wallpapers",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
                color = TextWhite
            )
            
            // Wallpapers grid
            when (uiState) {
                is UiState.Loading -> {
                    Text(
                        text = "Loading...",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                }
                is UiState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(wallpapers) { wallpaper ->
                            WallpaperCard(
                                wallpaper = wallpaper,
                                onClick = onWallpaperClick,
                                onArtistClick = onArtistClick
                            )
                        }
                    }
                }
                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        color = Color.Red
                    )
                }
            }
            
            // CTA button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { viewModel.loadRandomWallpapers() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryRed
                    )
                ) {
                    Text("Explore More")
                }
            }
        }
    }
}