package ru.kpfu.itis.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.kpfu.itis.db.dao.FavoriteSongDao
import ru.kpfu.itis.db.entity.FavoriteSongEntity

@Database(
    entities = [FavoriteSongEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract val favoriteSongDao: FavoriteSongDao

    companion object {
        const val DB_NAME = "genius.db"
    }
}