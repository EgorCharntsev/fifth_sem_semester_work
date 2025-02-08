package ru.kpfu.itis.impl.data.repository

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kpfu.itis.api.domain.model.AlbumDomainModel
import ru.kpfu.itis.api.domain.model.ArtistDomainModel
import ru.kpfu.itis.api.domain.model.DomainSearchable
import ru.kpfu.itis.api.domain.model.ItemType
import ru.kpfu.itis.api.domain.model.SearchResultDomainModel
import ru.kpfu.itis.api.domain.model.SectionDomainModel
import ru.kpfu.itis.api.domain.model.SongDomainModel
import ru.kpfu.itis.api.domain.model.SongLyricsDomainModel
import ru.kpfu.itis.api.domain.repository.GeniusRepository
import ru.kpfu.itis.common.domain.model.SectionType
import ru.kpfu.itis.impl.data.mapper.AlbumDomainModelMapper
import ru.kpfu.itis.impl.data.mapper.ArtistDomainModelMapper
import ru.kpfu.itis.impl.data.mapper.SongDomainModelMapper
import ru.kpfu.itis.impl.data.mapper.SongLyricsDomainModelMapper
import ru.kpfu.itis.network.api.GeniusLyricsApi
import ru.kpfu.itis.network.api.PublicGeniusApi
import ru.kpfu.itis.network.model.AlbumDataModel
import ru.kpfu.itis.network.model.ArtistDataModel
import ru.kpfu.itis.network.model.SongDataModel
import ru.kpfu.itis.common.exception.EmptyAlbumException
import ru.kpfu.itis.common.exception.EmptyAlbumSongsException
import ru.kpfu.itis.common.exception.EmptyArtistException
import ru.kpfu.itis.common.exception.EmptyArtistSongsException
import ru.kpfu.itis.common.exception.EmptyChartException
import ru.kpfu.itis.common.exception.EmptyLyricsException
import ru.kpfu.itis.common.exception.EmptySearchException
import ru.kpfu.itis.common.exception.EmptySongException
import javax.inject.Inject


class GeniusRepositoryImpl @Inject constructor(
    private val publicApi: PublicGeniusApi,
    private val lyricsApi: GeniusLyricsApi,
    private val songMapper: SongDomainModelMapper,
    private val songLyricsMapper: SongLyricsDomainModelMapper,
    private val albumMapper: AlbumDomainModelMapper,
    private val artistMapper: ArtistDomainModelMapper,
    private val gson: Gson,
) : GeniusRepository {

    override suspend fun getSongById(id: Long): SongDomainModel {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getSongById(id)
            songMapper.mapSongDataToDomain(resp?.data) ?: throw EmptySongException()
        }
    }

    override suspend fun getSongsChart(): List<SongDomainModel> {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getSongCharts()
            resp?.data
                ?.map { it.item }
                ?.map { songMapper.mapSongDataToDomain(it) ?: throw EmptySongException() }
                ?: throw EmptyChartException()
        }
    }

    override suspend fun getSongLyrics(url: String): SongLyricsDomainModel {
        return withContext(Dispatchers.IO) {
            val resp = lyricsApi.getLyrics(url)
            songLyricsMapper.mapSongLyricsDataToDomain(resp)
                ?: throw EmptyLyricsException()
        }
    }

    override suspend fun search(query: String): SearchResultDomainModel {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.search(query)
            val sections = resp?.response?.let { searchResp ->
                searchResp.sections.mapNotNull { section ->
                    val sectionType = SectionType.getByType(section.type) ?: return@mapNotNull null
                    SectionDomainModel(
                        sectionType,
                        section.hits.mapNotNull { hit ->
                            ItemType.getByType(hit.type)?.let { itemType ->
                                mapJsonObjectToSearchable(itemType, hit.result)
                            }
                        }
                    )
                }
            } ?: throw EmptySearchException()
            SearchResultDomainModel(sections)
        }
    }

    override suspend fun getAlbumById(id: Long): AlbumDomainModel {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getAlbumById(id)
            albumMapper.mapAlbumDataToDomain(resp?.response?.album)
                ?: throw EmptyAlbumException()
        }
    }

    override suspend fun getAlbumTracks(id: Long): List<SongDomainModel> {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getAlbumTracks(id)
            resp?.response?.tracks?.mapNotNull { songMapper.mapSongDataToDomain(it.song) }
                ?: throw EmptyAlbumSongsException()
        }
    }

    override suspend fun getArtistById(id: Long): ArtistDomainModel {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getArtistById(id)
            artistMapper.mapArtistDataToDomain(resp?.response?.artist)
                ?: throw EmptyArtistException()
        }
    }

    override suspend fun getArtistSongs(id: Long): List<SongDomainModel> {
        return withContext(Dispatchers.IO) {
            val resp = publicApi.getArtistSongs(id)
            resp?.response?.songs?.mapNotNull { songMapper.mapSongDataToDomain(it) }
                ?: throw EmptyArtistSongsException()
        }
    }

    private fun mapJsonObjectToSearchable(type: ItemType, json: JsonObject): DomainSearchable? {
        return when (type) {
            ItemType.SONG -> songMapper.mapSongDataToDomain(
                gson.fromJson(
                    json,
                    SongDataModel::class.java
                )
            )
            ItemType.ALBUM -> albumMapper.mapAlbumDataToDomain(
                gson.fromJson(
                    json,
                    AlbumDataModel::class.java
                )
            )
            ItemType.ARTIST -> artistMapper.mapArtistDataToDomain(
                gson.fromJson(
                    json,
                    ArtistDataModel::class.java
                )
            )
        }
    }
}