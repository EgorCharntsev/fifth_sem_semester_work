package ru.kpfu.itis.network.response

import ru.kpfu.itis.network.model.ArtistDataModel

data class ArtistResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    data class Inner(val artist: ArtistDataModel)
}