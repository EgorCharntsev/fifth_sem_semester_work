package ru.kpfu.itis.common.presentation.model

import java.util.Date

data class AlbumUiModel(
    override val id: Long,
    val name: String,
    val artist: ArtistUiModel,
    val description: String?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val releaseDate: Date?,
) : UiSearchable