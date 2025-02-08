package ru.kpfu.itis.api.domain.model

enum class ItemType(
    val type: String,
) {
    SONG("song"),
    ALBUM("album"),
    ARTIST("artist");

    companion object {
        fun getByType(type: String): ItemType?
                = ItemType.entries.find { it.type == type }
    }
}