package com.example.otakuscape.domain.usecase

import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.repository.AnimeRepository
import javax.inject.Inject

class GetRandomImagesUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(
        limit: Int? = null,
        offset: Int? = null,
        tags: String? = null,
        withoutTags: String? = null,
        rating: String? = null,
        artist: String? = null
    ): Result<List<Item>> {
        return repository.getRandomImages(limit, offset, tags, withoutTags, rating, artist)
    }
}