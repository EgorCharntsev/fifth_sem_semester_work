package ru.kpfu.itis.common.presentation.model

import ru.kpfu.itis.common.domain.model.SectionType

data class SectionUiModel(
    val type: SectionType,
    val items: List<UiSearchable>,
) : SearchResultItemModel