package ru.kpfu.itis.common.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

sealed class Route {

    object BottomMenu {
        @Serializable
        data object Profile : Route()
        @Serializable
        data object Favourites : Route()
        @Serializable
        data object Home: Route()
        @Serializable
        data object Search : Route()
    }

    @Serializable
    data class SongDetails(val songId: String) : Route()

    @Serializable
    data class AlbumDetails(val songId: String) : Route()

    @Serializable
    data class ArtistDetails(val songId: String) : Route()
}