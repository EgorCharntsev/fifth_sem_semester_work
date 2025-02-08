package ru.kpfu.itis.impl.usecase

import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import ru.kpfu.itis.common.presentation.model.FavoriteSongUiModel
import ru.kpfu.itis.impl.presentation.mapper.FavoriteSongUiModelMapper
import javax.inject.Inject

class SaveFavoriteSongUseCase @Inject constructor(
    private val repository: FavoriteSongsRepository,
    private val mapper: FavoriteSongUiModelMapper,
) {

    suspend operator fun invoke(song: FavoriteSongUiModel) {
        repository.saveFavorite(mapper.mapFavoriteSongUiToDomain(song))
    }
}