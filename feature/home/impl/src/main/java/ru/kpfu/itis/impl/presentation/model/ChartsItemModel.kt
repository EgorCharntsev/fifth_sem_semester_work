package ru.kpfu.itis.impl.presentation.model

import ru.kpfu.itis.common.presentation.model.SongUiModel

sealed class ChartsItemModel

data object ChartsHeaderItemModel : ChartsItemModel()

data class ChartsSongItemModel(
    val position: Int,
    val song: SongUiModel,
) : ChartsItemModel()