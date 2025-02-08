package ru.kpfu.itis.impl.usecase

import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import javax.inject.Inject

class DeleteFavoriteSongUseCase @Inject constructor(
    private val repository: FavoriteSongsRepository,
) {

    suspend operator fun invoke(id: Long) {
        repository.deleteFavorite(id)
    }
}