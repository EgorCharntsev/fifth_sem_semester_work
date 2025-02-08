package ru.kpfu.itis.api.domain.repository

import ru.kpfu.itis.api.domain.model.AlbumDomainModel
import ru.kpfu.itis.api.domain.model.ArtistDomainModel
import ru.kpfu.itis.api.domain.model.SearchResultDomainModel
import ru.kpfu.itis.api.domain.model.SongDomainModel
import ru.kpfu.itis.api.domain.model.SongLyricsDomainModel


interface GeniusRepository {
    suspend fun getSongById(id: Long): SongDomainModel
    suspend fun getSongsChart(): List<SongDomainModel>
    suspend fun getSongLyrics(url: String): SongLyricsDomainModel
    suspend fun search(query: String): SearchResultDomainModel
    suspend fun getAlbumById(id: Long): AlbumDomainModel
    suspend fun getAlbumTracks(id: Long): List<SongDomainModel>
    suspend fun getArtistById(id: Long): ArtistDomainModel
    suspend fun getArtistSongs(id: Long): List<SongDomainModel>
}