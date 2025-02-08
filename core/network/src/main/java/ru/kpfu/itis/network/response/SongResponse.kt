package ru.kpfu.itis.network.response

import ru.kpfu.itis.network.model.SongDataModel

data class SongResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    val data get() = response?.song
    data class Inner(val song: SongDataModel)
}
