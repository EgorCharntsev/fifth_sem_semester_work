package ru.kpfu.itis.common.presentation.model

import java.util.Date

data class FavoriteSongUiModel(
    val id: Long,
    val title: String,
    val artists: String,
    val imageUrl: String?,
    val timestamp: Date,
)