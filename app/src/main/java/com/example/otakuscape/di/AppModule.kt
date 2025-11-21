package com.example.otakuscape.di

import android.content.Context
import com.example.otakuscape.data.FavoritesManager
import com.example.otakuscape.data.remote.AnimeApiService
import com.example.otakuscape.data.remote.RetrofitClient
import com.example.otakuscape.data.repository.AnimeRepositoryImpl
import com.example.otakuscape.data.repository.FavoritesRepositoryImpl
import com.example.otakuscape.data.repository.WallpaperRepository
import com.example.otakuscape.domain.repository.AnimeRepository
import com.example.otakuscape.domain.repository.FavoritesRepository
import com.example.otakuscape.domain.usecase.DownloadImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeApiService(): AnimeApiService {
        return RetrofitClient.animeApiService
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(apiService: AnimeApiService): AnimeRepository {
        return AnimeRepositoryImpl(apiService)
    }
    
    @Provides
    @Singleton
    fun provideFavoritesRepository(favoritesManager: FavoritesManager): FavoritesRepository {
        return FavoritesRepositoryImpl(favoritesManager)
    }
    
    @Provides
    @Singleton
    fun provideWallpaperRepository(@ApplicationContext context: Context): WallpaperRepository {
        return WallpaperRepository(context)
    }
    
    @Provides
    @Singleton
    fun provideDownloadImageUseCase(@ApplicationContext context: Context): DownloadImageUseCase {
        return DownloadImageUseCase(context)
    }
}