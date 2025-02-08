package ru.kpfu.itis.network.response

import ru.kpfu.itis.network.model.AlbumDataModel

data class AlbumResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    data class Inner(val album: AlbumDataModel)
}
