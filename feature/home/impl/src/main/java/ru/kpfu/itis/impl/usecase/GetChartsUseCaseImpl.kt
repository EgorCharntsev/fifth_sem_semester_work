package ru.kpfu.itis.impl.usecase


import ru.kpfu.itis.api.domain.repository.GeniusRepository
import ru.kpfu.itis.common.presentation.model.SongUiModel
import ru.kpfu.itis.impl.presentation.mapper.SongUiModelMapper
import javax.inject.Inject

class GetChartsUseCaseImpl @Inject constructor(
    private val geniusRepository: GeniusRepository,
    private val mapper: SongUiModelMapper,
) {
    suspend operator fun invoke(): List<SongUiModel> {
        return geniusRepository.getSongsChart().map(mapper::mapSongDomainToUi)
    }
}