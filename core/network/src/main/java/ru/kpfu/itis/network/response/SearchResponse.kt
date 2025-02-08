package ru.kpfu.itis.network.response

import com.google.gson.JsonObject

data class SearchResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    data class Inner(val sections: List<Section>)
    data class Section(
        val type: String,
        val hits: List<Hit>
    )
    data class Hit(
        val type: String,
        val result: JsonObject,
    )
}