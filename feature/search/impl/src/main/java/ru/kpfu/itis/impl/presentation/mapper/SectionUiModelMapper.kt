package ru.kpfu.itis.impl.presentation.mapper

import ru.kpfu.itis.api.domain.model.AlbumDomainModel
import ru.kpfu.itis.api.domain.model.ArtistDomainModel
import ru.kpfu.itis.api.domain.model.SectionDomainModel
import ru.kpfu.itis.api.domain.model.SongDomainModel
import ru.kpfu.itis.common.presentation.model.SectionUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SectionUiModelMapper @Inject constructor(
    private val songMapper: SongUiModelMapper,
    private val albumMapper: AlbumUiModelMapper,
    private val artistMapper: ArtistUiModelMapper,
) {
    fun mapSectionDomainToUi(domain: SectionDomainModel): SectionUiModel {
        return SectionUiModel(
            type = domain.sectionType,
            items = domain.items.map { item ->
                when (item) {
                    is AlbumDomainModel -> albumMapper.mapAlbumDomainToUi(item)
                    is ArtistDomainModel -> artistMapper.mapArtistDomainToUi(item)
                    is SongDomainModel -> songMapper.mapSongDomainToUi(item)
                }
            }
        )
    }
}