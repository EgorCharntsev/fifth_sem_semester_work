package ru.kpfu.itis.impl.data.mapper

import android.icu.text.SimpleDateFormat
import ru.kpfu.itis.api.domain.model.SongDomainModel
import ru.kpfu.itis.network.model.SongDataModel
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongDomainModelMapper @Inject constructor() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun mapSongDataToDomain(data: SongDataModel?): SongDomainModel? {
        return data?.run {
            val songReleaseDate = if (releaseDateComponents != null) {
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
            SongDomainModel(
                id = id,
                artists = artists,
                title = title,
                description = description?.plain,
                releaseDate = songReleaseDate,
                thumbnailUrl = thumbnailUrl,
                imageUrl = imageUrl,
                pageViews = stats?.pageViews ?: 0,
                url = url,
            )
        }
    }
}