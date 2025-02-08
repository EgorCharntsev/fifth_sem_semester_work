package ru.kpfu.itis.api.model

import java.util.Date

data class FavoriteSongDomainModel(
    val id: Long,
    val title: String,
    val artists: String,
    val imageUrl: String?,
    val timestamp: Date,
)
