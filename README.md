# OtakuScape

An Android wallpaper application that provides a curated collection of anime-themed wallpapers from the NekosAPI v4.
https://github.com/Ankitgujare/Otakuspace-Wallpaper-Application/blob/a4411f216d91ac3db3947731106b3145ed76468f/five.jpeg

## Features

### Core Functionality
- **Browse Wallpapers**: Discover a vast collection of anime-themed wallpapers
- **Search**: Find specific wallpapers using tags or keywords
- **Categories**: Quick access to popular categories like Catgirls, Blue, and Red themed wallpapers
- **Safe Mode**: Toggle to filter content based on rating preferences
- **Favorites**: Save your favorite wallpapers for quick access
- **Download**: Save wallpapers directly to your device
- **Artist Profiles**: View wallpapers by specific artists
- **Recommendations**: Get personalized recommendations based on your preferences

### Advanced Features
- **Color-Based Filtering**: Filter wallpapers by dominant colors (Blue, Red, etc.)
- **Tag-Based Discovery**: Explore wallpapers using detailed tagging system
- **Rating System**: Content filtering with Safe, Suggestive, Borderline, and Explicit ratings
- **Auto-Refresh**: Automatically loads new wallpapers when scrolling
- **High-Quality Images**: Support for HD and 4K wallpapers
- **Dark Theme**: Anime-inspired dark UI with vibrant accent colors

### Technical Implementation
- **Clean Architecture**: Follows MVVM pattern with clear separation of concerns
- **Dependency Injection**: Uses Hilt for dependency management
- **Modern Android Development**: Built with Jetpack Compose, Coroutines, and Flow
- **REST API Integration**: Connects to NekosAPI v4 for wallpaper data
- **Image Loading**: Efficient image loading with Coil library
- **Offline Support**: Basic caching for improved performance

## Implemented Features

### UI/UX
- Dark-themed interface with anime aesthetic
- Responsive design that works on various screen sizes
- Intuitive navigation with bottom tab bar
- Smooth animations and transitions
- Custom components for displaying wallpaper metadata

### Screens
1. **Home Screen**
   - Search functionality
   - Safe mode toggle
   - Quick action buttons (Catgirls, Blue, Red)
   - Featured wallpapers grid

2. **Favorites Screen**
   - Saved wallpapers collection
   - Easy management of favorite items

3. **Recommendations Screen**
   - Personalized wallpaper suggestions
   - Based on previously viewed content

4. **Profile Screen**
   - User preferences and settings

5. **Wallpaper Detail Screen**
   - Full-screen wallpaper preview
   - Metadata display (tags, colors, source, artist)
   - Favorite toggle
   - Download functionality

### Data Management
- Local favorites storage using JSON serialization
- Efficient API calls with caching
- Error handling and loading states
- Repository pattern for data access

### API Integration
- NekosAPI v4 integration
- Support for various query parameters:
  - Limit (set to 100 for maximum results)
  - Tags filtering
  - Rating filtering (Safe, Suggestive, Borderline, Explicit)
  - Artist filtering
  - Offset for pagination

## Getting Started

### Prerequisites
- Android Studio Flamingo or later
- Kotlin 1.7.20+
- Android Gradle Plugin 8.0.2
- Minimum SDK level 28 (Android 9.0)

### Installation
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle dependencies
4. Build and run the application

### Building
```bash
./gradlew assembleDebug
```

## Architecture

The application follows Clean Architecture principles with three main layers:

1. **Presentation Layer**: Compose UI, ViewModels
2. **Domain Layer**: Use cases, business logic
3. **Data Layer**: Repositories, API services, local storage

## Libraries Used
- Jetpack Compose for UI
- Hilt for Dependency Injection
- Retrofit for API calls
- Coil for image loading
- Gson for JSON serialization
- Navigation Component for screen navigation
- Material3 for UI components

## Contributing
This is a demonstration project. Feel free to fork and modify as needed.

## License
This project is for educational purposes only.
