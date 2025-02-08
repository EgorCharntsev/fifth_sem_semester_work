package ru.kpfu.itis.network.response

import com.google.gson.annotations.SerializedName
import ru.kpfu.itis.network.model.SongDataModel

data class SongsChartResponse(
    val meta: GeniusResponseMeta,
    val response: Inner?,
) {
    val data get() = response?.chartItems
    data class Inner(@SerializedName("chart_items") val chartItems: List<ChartItem>)
    data class ChartItem(val item: SongDataModel)
}