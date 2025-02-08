package ru.kpfu.itis.api.domain.model

import java.util.Date

data class AlbumDomainModel(
    val id: Long,
    val name: String,
    val artist: ArtistDomainModel,
    val description: String?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val releaseDate: Date?,
) : DomainSearchable