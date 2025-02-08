package ru.kpfu.itis.impl.usecase

import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: FavoriteSongsRepository,
) {

    suspend operator fun invoke(id: Long): Boolean {
        return repository.isFavorite(id)
    }
}