package ru.kpfu.itis.common.presentation.model

data class ArtistUiModel(
    val name: String,
    val imageUrl: String?,
    val description: String?,
    override val id: Long,
) : UiSearchable