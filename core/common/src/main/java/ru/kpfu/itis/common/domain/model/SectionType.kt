package ru.kpfu.itis.common.domain.model

enum class SectionType(
    val type: String,
) {
    TOP_RESULT("top_hit"),
    SONG("song"),
    ALBUM("album"),
    ARTIST("artist");

    companion object {
        fun getByType(type: String): SectionType?
                = entries.find { it.type == type }
    }
}