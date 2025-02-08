package ru.kpfu.itis.network.api

import ru.kpfu.itis.network.model.SongLyricsDataModel

interface GeniusLyricsApi {
    suspend fun getLyrics(url: String): SongLyricsDataModel?
}