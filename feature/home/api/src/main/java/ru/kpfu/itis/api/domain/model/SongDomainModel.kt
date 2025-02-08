package ru.kpfu.itis.api.domain.model

import java.util.Date

data class SongDomainModel(
    val id: Long,
    val artists: String,
    val title: String,
    val description: String?,
    val releaseDate: Date?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val pageViews: Int,
    val url: String,
) : DomainSearchable