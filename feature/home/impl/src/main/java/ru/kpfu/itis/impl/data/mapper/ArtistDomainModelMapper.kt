package ru.kpfu.itis.impl.data.mapper

import ru.kpfu.itis.api.domain.model.ArtistDomainModel
import ru.kpfu.itis.network.model.ArtistDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArtistDomainModelMapper @Inject constructor() {

    fun mapArtistDataToDomain(data: ArtistDataModel?): ArtistDomainModel? {
        return data?.run {
            ArtistDomainModel(
                id = id,
                name = name,
                imageUrl = imageUrl,
                description = description?.plain,
            )
        }
    }
}