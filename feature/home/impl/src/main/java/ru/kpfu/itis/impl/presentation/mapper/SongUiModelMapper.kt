package ru.kpfu.itis.impl.presentation.mapper

import kotlinx.coroutines.runBlocking
import ru.kpfu.itis.api.domain.model.SongDomainModel
import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import ru.kpfu.itis.genius.util.formatted
import ru.kpfu.itis.common.presentation.model.SongUiModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SongUiModelMapper @Inject constructor(
    private val favoriteSongsRepository: FavoriteSongsRepository,
) {

    fun mapSongDomainToUi(domain: SongDomainModel): SongUiModel {
        return with(domain) {
            runBlocking {
                SongUiModel(
                    id = id,
                    artists = artists,
                    title = title,
                    description = description,
                    releaseDate = releaseDate,
                    thumbnailUrl = thumbnailUrl,
                    imageUrl = imageUrl,
                    pageViews = pageViews,
                    pageViewsFormatted = pageViews.formatted,
                    url = url,
                    isFavorite = favoriteSongsRepository.isFavorite(id)
                )
            }
        }
    }
}
