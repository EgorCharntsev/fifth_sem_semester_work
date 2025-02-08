package ru.kpfu.itis.impl.presentation.mapper

import ru.kpfu.itis.api.model.FavoriteSongDomainModel
import ru.kpfu.itis.common.presentation.model.FavoriteSongUiModel
import javax.inject.Inject

class FavoriteSongUiModelMapper @Inject constructor() {

    fun mapFavoriteSongDomainToUi(domain: FavoriteSongDomainModel): FavoriteSongUiModel {
        return with(domain) {
            FavoriteSongUiModel(
                id = id,
                title = title,
                artists = artists,
                imageUrl = imageUrl,
                timestamp = timestamp,
            )
        }
    }

    fun mapFavoriteSongUiToDomain(ui: FavoriteSongUiModel): FavoriteSongDomainModel {
        return with(ui) {
            FavoriteSongDomainModel(
                id = id,
                title = title,
                artists = artists,
                imageUrl = imageUrl,
                timestamp = timestamp,
            )
        }
    }
}