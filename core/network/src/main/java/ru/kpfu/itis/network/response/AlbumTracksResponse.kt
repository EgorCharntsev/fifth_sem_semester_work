package ru.kpfu.itis.network.response

import ru.kpfu.itis.network.model.SongDataModel

data class AlbumTracksResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    data class Inner(val tracks: List<NumberedSong>)
    data class NumberedSong(val number: Int, val song: SongDataModel)
}
