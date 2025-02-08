package ru.kpfu.itis.network.model

import com.google.gson.annotations.SerializedName

data class ArtistDataModel(
    val id: Long,
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    val description: Description?,
) {
    data class Description(val plain: String?)
}