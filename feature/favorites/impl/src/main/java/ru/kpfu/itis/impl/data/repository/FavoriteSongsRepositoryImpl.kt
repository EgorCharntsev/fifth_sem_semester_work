package ru.kpfu.itis.impl.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kpfu.itis.api.model.FavoriteSongDomainModel
import ru.kpfu.itis.api.repository.FavoriteSongsRepository
import ru.kpfu.itis.db.AppDatabase
import ru.kpfu.itis.impl.data.mapper.FavoriteSongDomainModelMapper
import javax.inject.Inject

class FavoriteSongsRepositoryImpl @Inject constructor(
    private val db: AppDatabase,
    private val mapper: FavoriteSongDomainModelMapper,
)  : FavoriteSongsRepository {

    override suspend fun getRecentFavorites(): List<FavoriteSongDomainModel> {
        return withContext(Dispatchers.IO) {
            db.favoriteSongDao.getRecentFavorites().map(mapper::mapFavoriteSongEntityToDomain)
        }
    }

    override suspend fun saveFavorite(fav: FavoriteSongDomainModel) {
        withContext(Dispatchers.IO) {
            db.favoriteSongDao.saveFavorite(mapper.mapFavoriteSongDomainToEntity(fav))
        }
    }

    override suspend fun deleteFavorite(id: Long) {
        withContext(Dispatchers.IO) {
            db.favoriteSongDao.deleteFavorite(id)
        }
    }

    override suspend fun isFavorite(id: Long): Boolean {
        return withContext(Dispatchers.IO) {
            db.favoriteSongDao.isFavorite(id)
        }
    }
}