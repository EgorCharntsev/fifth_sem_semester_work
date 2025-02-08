package ru.kpfu.itis.impl.data.mapper

import ru.kpfu.itis.api.domain.model.SongLyricsDomainModel
import ru.kpfu.itis.network.model.SongLyricsDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongLyricsDomainModelMapper @Inject constructor() {

    fun mapSongLyricsDataToDomain(data: SongLyricsDataModel?): SongLyricsDomainModel? {
        return data?.let {
            SongLyricsDomainModel(it.lyrics)
        }
    }
}