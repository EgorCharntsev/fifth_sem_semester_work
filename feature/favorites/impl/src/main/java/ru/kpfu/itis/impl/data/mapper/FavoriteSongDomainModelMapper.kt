package ru.kpfu.itis.impl.data.mapper

import ru.kpfu.itis.api.model.FavoriteSongDomainModel
import ru.kpfu.itis.db.entity.FavoriteSongEntity
import java.time.Instant
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteSongDomainModelMapper @Inject constructor() {

    fun mapFavoriteSongEntityToDomain(entity: FavoriteSongEntity): FavoriteSongDomainModel {
        return with(entity) {
            FavoriteSongDomainModel(
                id = id,
                title = title,
                artists = artists,
                imageUrl = imageUrl,
                timestamp = Date.from(Instant.ofEpochMilli(timestamp)),
            )
        }
    }

    fun mapFavoriteSongDomainToEntity(domain: FavoriteSongDomainModel): FavoriteSongEntity {
        return with(domain) {
            FavoriteSongEntity(
                id = id,
                title = title,
                artists = artists,
                imageUrl = imageUrl,
                timestamp = timestamp.time,
            )
        }
    }
}