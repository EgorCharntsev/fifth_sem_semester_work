package ru.kpfu.itis.network.model

import com.google.gson.annotations.SerializedName

data class SongDataModel(
    val id: Long,
    @SerializedName("artist_names")
    val artists: String,
    val title: String,
    val description: Description?,
    val stats: Stats?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("release_date_components")
    val releaseDateComponents: DateComponents?,
    @SerializedName("song_art_image_thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("song_art_image_url")
    val imageUrl: String?,
    @SerializedName("url")
    val url: String,
) {
    data class Description(val plain: String?)
    data class DateComponents(val year: Int, val month: Int, val day: Int)
    data class Stats(@SerializedName("pageviews") val pageViews: Int?)
}
