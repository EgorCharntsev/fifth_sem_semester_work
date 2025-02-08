package ru.kpfu.itis.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.kpfu.itis.db.entity.FavoriteSongEntity

@Dao
interface FavoriteSongDao {

    @Query("SELECT * FROM favorite_songs ORDER BY timestamp DESC")
    suspend fun getRecentFavorites(): List<FavoriteSongEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveFavorite(fav: FavoriteSongEntity)

    @Query("DELETE FROM favorite_songs WHERE id = :id")
    suspend fun deleteFavorite(id: Long)

    @Query("SELECT EXISTS(SELECT id FROM favorite_songs WHERE id = :id)")
    fun isFavorite(id: Long): Boolean
}