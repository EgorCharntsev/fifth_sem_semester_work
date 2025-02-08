package ru.kpfu.itis.common.presentation.model

sealed interface UiSearchable : SearchResultItemModel {
    val id : Long
}