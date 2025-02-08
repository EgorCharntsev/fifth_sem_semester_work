package ru.kpfu.itis.api.repository

import ru.kpfu.itis.api.model.FavoriteSongDomainModel

interface FavoriteSongsRepository {
    suspend fun getRecentFavorites(): List<FavoriteSongDomainModel>
    suspend fun saveFavorite(fav: FavoriteSongDomainModel)
    suspend fun deleteFavorite(id: Long)
    suspend fun isFavorite(id: Long): Boolean
}