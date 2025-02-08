package ru.kpfu.itis.api.domain.model

data class ArtistDomainModel(
    val id: Long,
    val name: String,
    val imageUrl: String?,
    val description: String?,
) : DomainSearchable