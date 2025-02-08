package ru.kpfu.itis.common.presentation.model

data class SongLyricsUiModel (
    val lyrics: String,
) {

    val isEmpty get() = lyrics.isBlank()

    companion object {
        fun empty(): SongLyricsUiModel {
            return SongLyricsUiModel("")
        }
    }
}