package ru.kpfu.itis.network.response

import ru.kpfu.itis.network.model.SongDataModel

data class ArtistSongsResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    data class Inner(val songs: List<SongDataModel>)
}
