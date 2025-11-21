# OtakuScape - Anime Wallpaper Application

## Project Overview
This is an Android application for browsing and managing anime wallpapers. The app follows clean architecture principles and uses modern Android development practices.

## Features Implemented

### 1. Clean Architecture
- **Data Layer**: Repository implementations, API services, DTOs
- **Domain Layer**: Use cases, repository interfaces
- **Presentation Layer**: ViewModel, UI components
- **Dependency Injection**: Hilt for managing dependencies

### 2. API Integration
- Integration with NekosAPI v4 for fetching anime wallpapers
- Support for various API endpoints:
  - Get all images
  - Get random images
  - Filter by tags
  - Filter by rating
  - Get specific image by ID

### 3. UI Components
- **Home Screen**: Featured wallpapers with category filtering
- **Wallpaper Detail Screen**: Detailed view with download/set as wallpaper options
- **Favorites Screen**: Manage favorite wallpapers
- **Profile Screen**: User profile and stats
- **Wallpapers Screen**: Additional wallpaper browsing
- **Bottom Navigation**: Easy navigation between main sections
- **Custom Theme**: Dark theme with anime aesthetic colors

### 4. Functionality
- Browse anime wallpapers
- Filter wallpapers by categories/tags
- Download wallpapers to device
- Set wallpapers as device background
- Add/remove wallpapers from favorites
- View wallpaper details

## Technical Implementation

### Dependencies Used
- **Networking**: Retrofit, OkHttp
- **Image Loading**: Coil
- **Dependency Injection**: Hilt
- **Navigation**: Jetpack Navigation
- **UI**: Jetpack Compose
- **JSON Parsing**: Gson
- **Lifecycle**: ViewModel, LiveData

### Architecture Components
- **MVVM**: Model-View-ViewModel pattern
- **Repository Pattern**: For data management
- **Use Cases**: Encapsulated business logic
- **State Management**: Using StateFlow for reactive UI updates

### Key Classes

#### Data Layer
- `AnimeApiService`: Retrofit interface for API calls
- `AnimeRepositoryImpl`: Implementation of anime data repository
- `FavoritesManager`: Local storage for favorite wallpapers
- `FavoritesRepositoryImpl`: Implementation of favorites repository
- `WallpaperRepository`: Repository for wallpaper operations
- `Item`, `AnimeResponse`: Data transfer objects

#### Domain Layer
- `GetAllImagesUseCase`, `GetRandomImagesUseCase`: Use cases for fetching wallpapers
- `ManageFavoritesUseCase`: Use case for managing favorites
- `DownloadImageUseCase`: Use case for downloading images
- `SetWallpaperUseCase`: Use case for setting wallpapers

#### Presentation Layer
- `MainViewModel`: Main view model for UI state management
- `HomeScreen`, `WallpaperDetailScreen`, etc.: Compose UI screens

#### UI Components
- `WallpaperCard`: Display individual wallpapers
- `CategoryChip`: Filter category chips
- `BottomNavigationBar`: Navigation between screens

#### Utilities
- `ImageDownloader`: Handle image downloading
- `WallpaperSetter`: Handle setting wallpapers

## Project Structure
```
com.example.otakuscape
├── data
│   ├── remote
│   │   ├── dto
│   │   └── repository
│   └── repository
├── di
├── domain
│   ├── repository
│   └── usecase
├── presentation
├── ui
│   ├── components
│   ├── navigation
│   ├── screens
│   └── theme
└── utils
```

## How to Run
1. Open the project in Android Studio
2. Sync the project with Gradle files
3. Build and run the application

## Future Improvements
- Implement proper error handling and user feedback
- Add more advanced filtering options
- Implement offline caching
- Add user authentication
- Improve UI animations and transitions
- Add more detailed wallpaper information
- Implement search functionality