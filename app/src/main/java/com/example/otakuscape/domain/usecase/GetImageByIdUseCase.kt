package com.example.otakuscape.domain.usecase

import com.example.otakuscape.data.remote.dto.Item
import com.example.otakuscape.domain.repository.AnimeRepository
import javax.inject.Inject

class GetImageByIdUseCase @Inject constructor(
    private val repository: AnimeRepository
) {
    suspend operator fun invoke(id: Int): Result<Item> {
        return repository.getImageById(id)
    }
}