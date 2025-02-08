package ru.kpfu.itis.impl.usecase

import ru.kpfu.itis.api.domain.repository.GeniusRepository
import ru.kpfu.itis.common.presentation.model.SearchResultUiModel
import ru.kpfu.itis.impl.presentation.mapper.SearchResultsUiModelMapper
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val geniusRepository: GeniusRepository,
    private val searchResultsMapper: SearchResultsUiModelMapper,
) {
    suspend operator fun invoke(query: String): SearchResultUiModel {
        return searchResultsMapper.mapSearchResultsDomainToUi(geniusRepository.search(query))
    }
}