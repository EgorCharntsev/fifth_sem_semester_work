package ru.kpfu.itis.common.presentation.model

import java.util.Date

data class SongUiModel(
    override val id: Long,
    val artists: String,
    val title: String,
    val description: String?,
    val releaseDate: Date?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val pageViews: Int,
    val pageViewsFormatted: String,
    val url: String,
    val isFavorite: Boolean,
) : UiSearchable