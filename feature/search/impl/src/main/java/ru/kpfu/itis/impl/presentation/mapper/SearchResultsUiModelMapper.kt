package ru.kpfu.itis.impl.presentation.mapper

import ru.kpfu.itis.api.domain.model.SearchResultDomainModel
import ru.kpfu.itis.common.presentation.model.SearchResultUiModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchResultsUiModelMapper @Inject constructor(
    private val sectionMapper: SectionUiModelMapper,
) {
    fun mapSearchResultsDomainToUi(domain: SearchResultDomainModel): SearchResultUiModel {
        return SearchResultUiModel(
            domain.sections.map(sectionMapper::mapSectionDomainToUi)
        )
    }
}