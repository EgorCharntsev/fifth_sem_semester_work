package ru.kpfu.itis.impl.presentation.mapper

import ru.kpfu.itis.api.domain.model.AlbumDomainModel
import ru.kpfu.itis.common.presentation.model.AlbumUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumUiModelMapper @Inject constructor(
    private val artistMapper: ArtistUiModelMapper,
) {

    fun mapAlbumDomainToUi(domain: AlbumDomainModel): AlbumUiModel {
        return with(domain) {
            AlbumUiModel(
                id = id,
                name = name,
                artist = artistMapper.mapArtistDomainToUi(artist),
                description = description,
                thumbnailUrl = thumbnailUrl,
                imageUrl = imageUrl,
                releaseDate = releaseDate,
            )
        }
    }
}