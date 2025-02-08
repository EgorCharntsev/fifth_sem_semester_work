package ru.kpfu.itis.impl.data.mapper

import android.icu.text.SimpleDateFormat
import ru.kpfu.itis.api.domain.model.AlbumDomainModel
import ru.kpfu.itis.network.model.AlbumDataModel
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumDomainModelMapper @Inject constructor(
    private val artistMapper: ArtistDomainModelMapper,
) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun mapAlbumDataToDomain(data: AlbumDataModel?): AlbumDomainModel? {
        return data?.run {
            val albumReleaseDate = if (releaseDateComponents != null) {
                val cal = Calendar.getInstance()
                cal.set(
                    releaseDateComponents!!.year,
                    releaseDateComponents!!.month,
                    releaseDateComponents!!.day,
                )
                cal.time
            } else if (releaseDate != null) {
                dateFormat.parse(releaseDate)
            } else  {
                null
            }
            AlbumDomainModel(
                id = id,
                name = name,
                artist = artistMapper.mapArtistDataToDomain(artist)!!,
                description = description?.plain,
                thumbnailUrl = thumbnailUrl,
                imageUrl = imageUrl,
                releaseDate = albumReleaseDate,
            )
        }
    }
}