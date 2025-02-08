package ru.kpfu.itis.network.model

import com.google.gson.annotations.SerializedName

data class AlbumDataModel(
    val id: Long,
    val name: String,
    val artist: ArtistDataModel,
    val description: Description?,
    @SerializedName("cover_art_thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("cover_art_url")
    val imageUrl: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("release_date_components")
    val releaseDateComponents: DateComponents?,
) {
    data class Description(val plain: String?)
    data class DateComponents(val year: Int, val month: Int, val day: Int)
}
