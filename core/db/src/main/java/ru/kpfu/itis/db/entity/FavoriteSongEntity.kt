package ru.kpfu.itis.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_songs")
data class FavoriteSongEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val artists: String,
    val imageUrl: String?,
    val timestamp: Long,
)