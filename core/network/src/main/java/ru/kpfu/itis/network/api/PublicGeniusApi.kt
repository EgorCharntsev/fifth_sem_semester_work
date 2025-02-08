package ru.kpfu.itis.network.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kpfu.itis.network.response.AlbumResponse
import ru.kpfu.itis.network.response.AlbumTracksResponse
import ru.kpfu.itis.network.response.ArtistResponse
import ru.kpfu.itis.network.response.ArtistSongsResponse
import ru.kpfu.itis.network.response.SearchResponse
import ru.kpfu.itis.network.response.SongResponse
import ru.kpfu.itis.network.response.SongsChartResponse

interface PublicGeniusApi {

    @GET("songs/{id}?text_format=plain")
    suspend fun getSongById(@Path("id") id: Long): SongResponse?

    @GET("songs/chart")
    suspend fun getSongCharts(@Query("per_page") perPage: Int = 25): SongsChartResponse?

    @GET("search/multi")
    suspend fun search(@Query("q") query: String): SearchResponse?

    @GET("albums/{id}?text_format=plain")
    suspend fun getAlbumById(@Path("id") id: Long): AlbumResponse?

    @GET("albums/{id}/tracks")
    suspend fun getAlbumTracks(@Path("id") id: Long): AlbumTracksResponse?

    @GET("artists/{id}?text_format=plain")
    suspend fun getArtistById(@Path("id") id: Long): ArtistResponse?

    @GET("artists/{id}/songs?sort=popularity")
    suspend fun getArtistSongs(@Path("id") id: Long, @Query("per_page") perPage: Int = 25): ArtistSongsResponse?
}