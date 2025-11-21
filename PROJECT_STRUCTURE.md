# Project Structure

```
OtakuScape/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/otakuscape/
│   │   │   │   ├── data/
│   │   │   │   │   ├── remote/
│   │   │   │   │   │   ├── dto/
│   │   │   │   │   │   │   ├── Item.kt
│   │   │   │   │   │   │   └── anime.kt
│   │   │   │   │   │   ├── AnimeApiService.kt
│   │   │   │   │   │   └── RetrofitClient.kt
│   │   │   │   │   │   └── repository/
│   │   │   │   │   │       ├── AnimeRepositoryImpl.kt
│   │   │   │   │   │       ├── FavoritesRepositoryImpl.kt
│   │   │   │   │   │       └── WallpaperRepository.kt
│   │   │   │   │   ├── FavoritesManager.kt
│   │   │   │   ├── di/
│   │   │   │   │   └── AppModule.kt
│   │   │   │   ├── domain/
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── AnimeRepository.kt
│   │   │   │   │   │   └── FavoritesRepository.kt
│   │   │   │   │   └── usecase/
│   │   │   │   │       ├── DownloadImageUseCase.kt
│   │   │   │   │       ├── GetAllImagesUseCase.kt
│   │   │   │   │       ├── GetImageByIdUseCase.kt
│   │   │   │   │       ├── GetRandomImagesUseCase.kt
│   │   │   │   │       ├── ManageFavoritesUseCase.kt
│   │   │   │   │       └── SetWallpaperUseCase.kt
│   │   │   │   ├── presentation/
│   │   │   │   │   └── MainViewModel.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── components/
│   │   │   │   │   │   ├── BottomNavigationBar.kt
│   │   │   │   │   │   ├── CategoryChip.kt
│   │   │   │   │   │   └── WallpaperCard.kt
│   │   │   │   │   ├── navigation/
│   │   │   │   │   │   └── NavigationItem.kt
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── FavoritesScreen.kt
│   │   │   │   │   │   ├── HomeScreen.kt
│   │   │   │   │   │   ├── MainScreen.kt
│   │   │   │   │   │   ├── ProfileScreen.kt
│   │   │   │   │   │   ├── WallpaperDetailScreen.kt
│   │   │   │   │   │   ├── WallpaperDetailScreenV2.kt
│   │   │   │   │   │   └── WallpapersScreen.kt
│   │   │   │   │   └── theme/
│   │   │   │   │       ├── AnimeTheme.kt
│   │   │   │   │       ├── Color.kt
│   │   │   │   │       ├── Theme.kt
│   │   │   │   │       └── Type.kt
│   │   │   │   └── utils/
│   │   │   │       ├── ImageDownloader.kt
│   │   │   │       └── WallpaperSetter.kt
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   ├── drawable-v24/
│   │   │   │   ├── mipmap-anydpi-v26/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   │   ├── androidTest/
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── build.gradle
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── .gitignore
├── LICENSE
├── README.md
└── PROJECT_SUMMARY.md
```