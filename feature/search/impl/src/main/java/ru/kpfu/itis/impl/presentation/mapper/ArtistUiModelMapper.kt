package ru.kpfu.itis.impl.presentation.mapper

import ru.kpfu.itis.api.domain.model.ArtistDomainModel
import ru.kpfu.itis.common.presentation.model.ArtistUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtistUiModelMapper @Inject constructor() {

    fun mapArtistDomainToUi(domain: ArtistDomainModel): ArtistUiModel {
        return with(domain) {
            ArtistUiModel(
                id = id,
                name = name,
                imageUrl = imageUrl,
                description = description,
            )
        }
    }
}