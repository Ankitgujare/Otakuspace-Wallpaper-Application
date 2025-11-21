package com.example.otakuscape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.otakuscape.ui.screens.MainScreen
import com.example.otakuscape.ui.theme.OtakuScapeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtakuScapeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

/*@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    var selectedWallpaper by remember { mutableStateOf<Item?>(null) }
    
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onWallpaperClick = { wallpaper ->
                    selectedWallpaper = wallpaper
                    navController.navigate("wallpaper_detail/${wallpaper.id}")
                }
            )
        }
        
        composable("favorites") {
            // TODO: Implement Favorites screen
            androidx.compose.material3.Text(
                text = "Favorites Screen",
                color = androidx.compose.ui.graphics.Color.White,
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            )
        }
        
        composable("wallpapers") {
            // TODO: Implement Wallpapers screen
            androidx.compose.material3.Text(
                text = "Wallpapers Screen",
                color = androidx.compose.ui.graphics.Color.White,
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            )
        }
        
        composable("profile") {
            // TODO: Implement Profile screen
            androidx.compose.material3.Text(
                text = "Profile Screen",
                color = androidx.compose.ui.graphics.Color.White,
                modifier = androidx.compose.ui.Modifier.fillMaxSize()
            )
        }
        
        composable(
            "wallpaper_detail/{wallpaperId}",
            arguments = listOf(navArgument("wallpaperId") { type = NavType.IntType })
        ) { backStackEntry ->
            val wallpaperId = backStackEntry.arguments?.getInt("wallpaperId") ?: 0
            // In a real app, we would fetch the wallpaper by ID
            // For now, we'll use the selected wallpaper
            selectedWallpaper?.let { wallpaper ->
                WallpaperDetailScreen(
                    wallpaper = wallpaper,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}*/

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OtakuScapeTheme {
        AppNavigation()
    }
}*/